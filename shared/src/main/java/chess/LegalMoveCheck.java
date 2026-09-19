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
     *
     * @return
     */
    public Collection<ChessMove> legalMove(ChessPiece piece, ChessPosition position) {
        int beginRowPos = position.getRow();
        int beginColPos = position.getColumn();
        int endRowDist = 0;
        int endColDist = 0;
        ChessPiece.PieceType type = piece.getPieceType();

        List<ChessMove> legalMoveList = new ArrayList<ChessMove>();

        switch (type) {
            case KING:
                break;
            case QUEEN:
                break;
            case BISHOP:
                break;
            case KNIGHT:
                break;
            case ROOK:
                break;
            case PAWN:
                break;
        }

        return legalMoveList;
    }
    /*
     * this will only give the names of functions
     * this will NOT define any of the functions
     */
}
