package chess;

import java.util.Collection;
import java.util.Objects;
import java.util.ArrayList;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    ChessBoard gameBoard;
    TeamColor turn;
    ChessBoard hypothetical;

    public ChessGame() {
        this.gameBoard = new ChessBoard();
        this.turn = TeamColor.WHITE;
        this.hypothetical = new ChessBoard();
        gameBoard.resetBoard();
        hypothetical.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.turn;
    }

    /**
     * Sets which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.turn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    //FIX THIS FUNCTION
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if (gameBoard.getPiece(startPosition) == null) {
            return null;
        }
        ChessPiece toMove = gameBoard.getPiece(startPosition);
        Collection<ChessMove> validMoveList = toMove.pieceMoves(gameBoard, startPosition);
        Collection<ChessMove> toRemove = new ArrayList<>();
        ChessGame.TeamColor color = toMove.getTeamColor();
        ChessPiece.PieceType type;

        for ( ChessMove move : validMoveList ) {
            hypothetical = getBoard();
            if (move.getPromotionPiece() != null) {
                type = move.getPromotionPiece();
            }
            else {
                type = toMove.getPieceType();
            }
            hypothetical.addPiece(move.getEndPosition(), new ChessPiece(color,type));
            hypothetical.addPiece(startPosition, null);

            //Is team going to be in check?
            ChessPosition kingPos = getKingPos(color);
            for (int row = 1; row <= 8; row++) {
                for (int col = 1; col <= 8; col++) {
                    ChessPosition checkHere = new ChessPosition(row,col);
                    if (hypothetical.getPiece(checkHere) != null
                            && hypothetical.getPiece(checkHere).getTeamColor() != color) {
                        for ( ChessMove moves : hypothetical.getPiece(checkHere).pieceMoves(hypothetical, checkHere) ) {
                            if (moves.getEndPosition().equals(kingPos)) {
                                toRemove.add(move);
                            }
                        }
                    }
                }
            }
        }

        hypothetical = getBoard();

        for ( ChessMove invalidMove : toRemove ) {
            validMoveList.remove(invalidMove);
        }

        return validMoveList;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece moveCandidate = gameBoard.getPiece(move.getStartPosition());
        if (moveCandidate == null) {
            throw new InvalidMoveException("No piece exists here");
        }
        Collection<ChessMove> validMoveList = validMoves(move.getStartPosition());
        if (validMoveList == null) {
            throw new InvalidMoveException("No valid moves for this piece");
        }
        if (validMoveList.contains(move)) {
            TeamColor color = moveCandidate.getTeamColor();
            if (color == getTeamTurn()) {
                ChessPiece.PieceType type;
                if (move.getPromotionPiece() != null) {
                    type = move.getPromotionPiece();
                }
                else {
                    type = moveCandidate.getPieceType();
                }
                gameBoard.addPiece(move.getEndPosition(), new ChessPiece(color, type));
                gameBoard.addPiece(move.getStartPosition(), null);
                if (color == TeamColor.WHITE) {
                    setTeamTurn(TeamColor.BLACK);
                }
                else {
                    setTeamTurn(TeamColor.WHITE);
                }
            }
            else {
                throw new InvalidMoveException("This team cannot move on this turn");
            }
        }
        else {
            throw new InvalidMoveException("Illegal move, please make a different move");
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPos = getKingPos(teamColor);
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition checkHere = new ChessPosition(row,col);
                if (gameBoard.getPiece(checkHere) != null
                        && gameBoard.getPiece(checkHere).getTeamColor() != teamColor) {
                    for ( ChessMove moves : gameBoard.getPiece(checkHere).pieceMoves(gameBoard, checkHere) ) {
                        if (moves.getEndPosition().equals(kingPos)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            return false;
        }
        ChessPosition kingPos = getKingPos(teamColor);

        //Can any future moves save the king?
        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition toCheck = new ChessPosition(row, col);
                if (gameBoard.getPiece(toCheck) != null) {
                    ChessPiece pieceToCheck = gameBoard.getPiece(toCheck);
                    if (pieceToCheck.getTeamColor() == teamColor && !validMoves(toCheck).isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.gameBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.gameBoard;
    }

    public ChessPosition getKingPos(TeamColor color) {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition checkHere = new ChessPosition(row,col);
                if(gameBoard.getPiece(checkHere) != null) {
                    ChessPiece pieceToCheck = gameBoard.getPiece(checkHere);
                    if (pieceToCheck.getPieceType() == ChessPiece.PieceType.KING && pieceToCheck.getTeamColor() == color) {
                        return checkHere;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        ChessGame toComp = (ChessGame)obj;
        return toComp.gameBoard.equals(this.gameBoard)
                && toComp.turn.equals(this.turn)
                && toComp.hypothetical.equals(this.hypothetical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.gameBoard, this.turn, this.hypothetical);
    }
}
