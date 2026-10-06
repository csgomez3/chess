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
        setBoard(gameBoard);
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
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if (gameBoard.getPiece(startPosition) == null) {
            return null;
        }
        ChessPiece toMove = gameBoard.getPiece(startPosition);
        Collection<ChessMove> validMoveList = toMove.pieceMoves(gameBoard, startPosition);
        Collection<ChessMove> toRemove = new ArrayList<>();

        for ( ChessMove move : validMoveList ) {
            ChessPiece.PieceType type;
            ChessGame.TeamColor color = toMove.getTeamColor();
            hypotheticalBoardReset(gameBoard);
            if (move.getPromotionPiece() != null) {
                type = move.getPromotionPiece();
            }
            else {
                type = toMove.getPieceType();
            }
            hypothetical.addPiece(move.getEndPosition(), new ChessPiece(color, type));
            hypothetical.addPiece(move.getStartPosition(), null);
            if (isInCheck(color)) {
                toRemove.add(move);
            }
        }

        for ( ChessMove invalidMove : toRemove ) {
            validMoveList.remove(invalidMove);
        }

        hypotheticalBoardReset(gameBoard);
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
            throw new InvalidMoveException("Illegal move, please make a different move");
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
                if (type == ChessPiece.PieceType.KING) {
                    if (color == TeamColor.WHITE) {
                        setWhiteKingPos(move.getEndPosition());
                    }
                    else {
                        setBlackKingPos(move.getEndPosition());
                    }
                }
                if (color == TeamColor.WHITE) {
                    setTeamTurn(TeamColor.BLACK);
                }
                else {
                    setTeamTurn(TeamColor.WHITE);
                }
            }
            return;
        }
        throw new InvalidMoveException("Illegal move, please make a different move");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPos;
        int row;
        int col;
        if (teamColor == TeamColor.WHITE) {
            kingPos = whiteKingPos;
        }
        else {
            kingPos = blackKingPos;
        }
        for (row = 1; row <= 8; row++) {
            for (col = 1; col <= 8; col++) {
                ChessPosition checkHere = new ChessPosition(row,col);
                if (hypothetical.getPiece(checkHere) != null
                        && hypothetical.getPiece(checkHere).getTeamColor() != teamColor) {
                    for ( ChessMove moves : hypothetical.getPiece(checkHere).pieceMoves(hypothetical, checkHere) ) {
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
                ChessPiece type = board.getPiece(pos);
                this.hypothetical.addPiece(pos, type);
            }
        }
    }

    public void setWhiteKingPos(ChessPosition newKingPos) {
        whiteKingPos = newKingPos;
    }

    public void setBlackKingPos(ChessPosition newKingPos) {
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
