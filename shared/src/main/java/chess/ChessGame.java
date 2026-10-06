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
    ChessPosition whiteKingPos;
    ChessPosition blackKingPos;
    ChessBoard hypothetical;

    public ChessGame() {
        this.gameBoard = new ChessBoard();
        this.gameBoard.resetBoard();
        this.whiteKingPos = new ChessPosition(1,5);
        this.blackKingPos = new ChessPosition(8,5);
        this.turn = TeamColor.WHITE;
        this.hypothetical = new ChessBoard();
        hypotheticalBoardReset(this.gameBoard);
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
        ChessPosition kingPos;
        if (color == TeamColor.WHITE) {
            kingPos = whiteKingPos;
        }
        else {
            kingPos = blackKingPos;
        }

        for ( ChessMove move : validMoveList ) {
            hypotheticalBoardReset(gameBoard);
            ChessPiece.PieceType type;
            if (move.getPromotionPiece() != null) {
                type = move.getPromotionPiece();
            }
            else {
                type = toMove.getPieceType();
            }
            if (type == ChessPiece.PieceType.KING) {
                kingPos = move.getEndPosition();
            }
            hypothetical.addPiece(move.getEndPosition(), new ChessPiece(color, type));
            hypothetical.addPiece(move.getStartPosition(), null);
            for (int row = 1; row <= 8; row++) {
                for (int col = 1; col <= 8; col++) {
                    ChessPosition compare = new ChessPosition(row, col);
                    if (hypothetical.getPiece(compare) != null
                            && hypothetical.getPiece(compare).getTeamColor() != color) {
                        Collection<ChessMove> enemyMoves = hypothetical.getPiece(compare).pieceMoves(hypothetical,compare);
                        for ( ChessMove enemyMove : enemyMoves ) {
                            if (enemyMove.getEndPosition() == kingPos) {
                                toRemove.add(move);
                            }
                        }
                    }
                }
            }
        }

        hypotheticalBoardReset(gameBoard);

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
        Collection<ChessMove> validMoveList = validMoves(move.getStartPosition());
        if (validMoveList == null) {
            throw new InvalidMoveException("No valid moves for this piece");
        }
        if (validMoveList.contains(move)) {
            TeamColor color = gameBoard.getPiece(move.getStartPosition()).getTeamColor();
            if (color == getTeamTurn()) {
                ChessPiece.PieceType type;
                if (move.getPromotionPiece() != null) {
                    type = move.getPromotionPiece();
                }
                else {
                    type = gameBoard.getPiece(move.getStartPosition()).getPieceType();
                }
                gameBoard.addPiece(move.getEndPosition(), new ChessPiece(color, type));
                gameBoard.addPiece(move.getStartPosition(), null);
                if (type == ChessPiece.PieceType.KING) {
                    if (color == TeamColor.WHITE) {
                        storeWhiteKingPos(move.getEndPosition());
                    }
                    else {
                        storeBlackKingPos(move.getEndPosition());
                    }
                }
                if (color == TeamColor.WHITE) {
                    setTeamTurn(TeamColor.BLACK);
                }
                else {
                    setTeamTurn(TeamColor.WHITE);
                }
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
    //FIX THIS FUNCTION
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPos;
        if (teamColor == TeamColor.WHITE) {
            kingPos = whiteKingPos;
        }
        else {
            kingPos = blackKingPos;
        }
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition checkHere = new ChessPosition(row,col);
                if (gameBoard.getPiece(checkHere) != null
                        && gameBoard.getPiece(checkHere).getTeamColor() != teamColor) {
                    for ( ChessMove moves : gameBoard.getPiece(checkHere).pieceMoves(gameBoard, checkHere) ) {
                        if (moves.getEndPosition() == kingPos) {
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
        ChessPosition kingPos;
        if (teamColor == TeamColor.WHITE) {
            kingPos = whiteKingPos;
        }
        else {
            kingPos = blackKingPos;
        }
        return validMoves(kingPos) == null;
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
        if (teamColor == TeamColor.WHITE && validMoves(whiteKingPos) == null) {
            return true;
        }
        return teamColor == TeamColor.BLACK && validMoves(blackKingPos) == null;
    }

    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        board.resetBoard();
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.gameBoard;
    }

    public void hypotheticalBoardReset(ChessBoard board) {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition pos = new ChessPosition(row,col);
                this.hypothetical.addPiece(pos, null);
                ChessPiece type = board.getPiece(pos);
                this.hypothetical.addPiece(pos, type);
            }
        }
    }

    public void storeWhiteKingPos(ChessPosition newKingPos) {
        whiteKingPos = newKingPos;
    }

    public void storeBlackKingPos(ChessPosition newKingPos) {
        blackKingPos = newKingPos;
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
                && toComp.whiteKingPos.equals(this.whiteKingPos)
                && toComp.blackKingPos.equals(this.blackKingPos)
                && toComp.hypothetical.equals(this.hypothetical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.gameBoard, this.turn, this.whiteKingPos, this.blackKingPos, this.hypothetical);
    }
}
