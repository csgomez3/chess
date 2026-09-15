package chess;

import java.util.Collection;
import java.util.List;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);

        //Implement a LegalMove function, do so for each of the pieces
        //Do this in a new class file (LegalMoves.java) or in ChessPiece.java?
        //See Phase 0: Architectural Patterns for potential example

        //Move rules:
        //All: while index<8 && index>0
        //King: [r+1][c],[r+1][c+1],[r][c+1],[r-1][c+1],[r-1][c],[r-1][c-1],[r][c-1],[r+1][c-1]
        //Queen: [r+x][c],[r+x][c+x],[r][c+x],[r-x][c+x],[r-x][c],[r-x][c-x],[r][c-x],[r+x][c-x]
        //Rook: [r+x][c],[r][c+x],[r-x][c],[r][c-x]
        //Bishop: [r+x][c+x],[r-x][c+x],[r-x][c-x],[r+x][c-x]
        //Knight: [r+2][c+1],[r+1][c+2],[r-1][c+2],[r-2][c+1],[r-2][c-1],[r-1][c-2],[r+1][c-2],[r+2][c-1]
        //Pawn: [r+1][c]

        //Questions
        //Pawn: need rule for first turn jump? [r+2][c]
        //Pawn: need rule for taking a piece? [r+1][c-1],[r+1][c+1]

        return List.of();
    }
}
