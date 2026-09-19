package chess;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * make sure you write important notes about what this interface is
 * and what it does
 */
public class LegalMoveCheck {

    public Collection<ChessMove> legalMove(ChessPiece piece, ChessPosition position) {
        int beginRow = position.getRow();
        int beginCol = position.getColumn();
        ChessPiece.PieceType type = piece.getPieceType();

        int moveDist = 1;
        ChessPosition endPos;
        List<ChessMove> legalMoveList = new ArrayList<ChessMove>();

        switch (type) {
            case KING:
                if (beginRow < 8) {
                    endPos = forward(beginRow, beginCol, moveDist);
                    legalMoveList.add();
                }
                if (beginRow > 1) {
                    endPos = forward(beginRow, beginCol, moveDist);
                    legalMoveList.add();
                }
                if (beginCol > 1) {
                    endPos = left(beginRow, beginCol, moveDist);
                    legalMoveList.add();
                }
                if (beginCol < 8) {
                    endPos = right(beginRow, beginCol, moveDist);
                    legalMoveList.add();
                }
                if (beginRow && beginCol) {
                    endPos = diagonal(beginRow, beginCol, 1, 1);
                    legalMoveList.add();
                }
                if (beginRow && beginCol) {
                    endPos = diagonal(beginRow, beginCol, 1, 2);
                    legalMoveList.add();
                }
                if (beginRow && beginCol) {
                    endPos = diagonal(beginRow, beginCol, 1, 3);
                    legalMoveList.add();
                }
                if (beginRow && beginCol) {
                    endPos = diagonal(beginRow, beginCol, 1, 4);
                    legalMoveList.add();
                }
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

    //Move types
    public final ChessPosition forward(int beginRow, int beginCol, int moveDist) {
        int endRow = beginRow + moveDist;
        return new ChessPosition(endRow, beginCol);
    }

    public ChessPosition backward(int beginRow, int beginCol, int moveDist) {
        int endRow = beginRow - moveDist;
        return new ChessPosition(endRow, beginCol);
    }

    public ChessPosition left(int beginRow, int beginCol, int moveDist) {
        int endCol = beginCol - moveDist;
        return new ChessPosition(beginRow, endCol);
    }

    public ChessPosition right(int beginRow, int beginCol, int moveDist) {
        int endCol = beginCol + moveDist;
        return new ChessPosition(beginRow, endCol);
    }

    public ChessPosition diagonal(int beginRow, int beginCol, int moveDist, int quad) {
        int endRow;
        int endCol;

        if (quad == 1) {
            endRow = beginRow + moveDist;
            endCol = beginCol + moveDist;
        }
        else if (quad == 2) {
            endRow = beginRow + moveDist;
            endCol = beginCol - moveDist;
        }
        else if (quad == 3) {
            endRow = beginRow - moveDist;
            endCol = beginCol - moveDist;
        }
        else {
            endRow = beginRow - moveDist;
            endCol = beginCol + moveDist;
        }

        return new ChessPosition(endRow, endCol);
    }

    public ChessPosition LShape(int beginRow, int beginCol) {
        return new ChessPosition();
    }
}
