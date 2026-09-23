package chess;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * make sure you write important notes about what this interface is
 * and what it does
 */
public class LegalMoveCheck {

    /**
     * @return list of all moves that a given piece can make
     */
    public static Collection<ChessMove> legalMove(ChessPiece piece,
                                                  ChessPosition position, ChessBoard board) {

        ChessPiece.PieceType type = piece.getPieceType();

        List<ChessMove> legalMoveList = new ArrayList<>();

        switch (type) {
            case KING:
                LegalMoveAdder.northPathAdder(position, 1, board, piece, legalMoveList);
                LegalMoveAdder.southPathAdder(position, 1, board, piece, legalMoveList);
                LegalMoveAdder.westPathAdder(position, 1, board, piece, legalMoveList);
                LegalMoveAdder.eastPathAdder(position, 1, board, piece, legalMoveList);
                LegalMoveAdder.diagPathAdder(position, 1, board, piece, legalMoveList);
                break;
            case QUEEN:
                LegalMoveAdder.northPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.southPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.westPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.eastPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.diagPathAdder(position, 8, board, piece, legalMoveList);
                break;
            case BISHOP:
                LegalMoveAdder.diagPathAdder(position, 8, board, piece, legalMoveList);
                break;
            case KNIGHT:
                LegalMoveAdder.LPathAdder(position, board, piece, legalMoveList);
                break;
            case ROOK:
                LegalMoveAdder.northPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.southPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.westPathAdder(position, 8, board, piece, legalMoveList);
                LegalMoveAdder.eastPathAdder(position, 8, board, piece, legalMoveList);
                break;
            case PAWN:
                LegalMoveAdder.pawnPathAdder(position, board, piece, legalMoveList);
                break;
        }
        return legalMoveList;
    }
}
