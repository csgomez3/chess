package chess;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import chess.ChessPiece.PieceType;

/**
 * make sure you write important notes about what this interface is
 * and what it does
 */
public class LegalMoveCheck {

    /**
     * @return list of all moves that a given piece can make
     */
    public static Collection<ChessMove> legalMove(ChessPiece piece, ChessPosition position) {
        int beginRow = position.getRow();
        int beginCol = position.getColumn();
        ChessPiece.PieceType type = piece.getPieceType();

        int moveDist = 1;
        ChessPosition endPos;
        List<ChessMove> legalMoveList = new ArrayList<>();

        switch (type) {
            case KING:
                if (beginRow < 8) {
                    endPos = forward(beginRow, beginCol, moveDist);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginRow > 1) {
                    endPos = backward(beginRow, beginCol, moveDist);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginCol > 1) {
                    endPos = left(beginRow, beginCol, moveDist);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginCol < 8) {
                    endPos = right(beginRow, beginCol, moveDist);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginRow < 8 && beginCol < 8) {
                    endPos = diagonal(beginRow, beginCol, moveDist, 1);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginRow > 1 && beginCol < 8) {
                    endPos = diagonal(beginRow, beginCol, moveDist, 2);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginRow > 1 && beginCol > 1) {
                    endPos = diagonal(beginRow, beginCol, moveDist, 3);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                if (beginRow < 8 && beginCol > 1) {
                    endPos = diagonal(beginRow, beginCol, moveDist, 4);
                    legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                }
                break;

            case QUEEN:
                while (moveDist < 7) {
                    if (beginRow < 8) {
                        endPos = forward(beginRow, beginCol, moveDist);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginRow > 1) {
                        endPos = backward(beginRow, beginCol, moveDist);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginCol > 1) {
                        endPos = left(beginRow, beginCol, moveDist);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginCol < 8) {
                        endPos = right(beginRow, beginCol, moveDist);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginRow < 8 && beginCol < 8) {
                        endPos = diagonal(beginRow, beginCol, moveDist, 1);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginRow > 1 && beginCol < 8) {
                        endPos = diagonal(beginRow, beginCol, moveDist, 2);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginRow > 1 && beginCol > 1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, 3);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    if (beginRow < 8 && beginCol > 1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, 4);
                        legalMoveList.add(new ChessMove(position, endPos, PieceType.KING));
                    }
                    moveDist++;
                }
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

    /**
     * @return coordinates after forward move
     */
    public static ChessPosition forward(int beginRow, int beginCol, int moveDist) {
        int endRow = beginRow + moveDist;
        return new ChessPosition(endRow, beginCol);
    }

    /**
     * @return coordinates after backward move
     */
    public static ChessPosition backward(int beginRow, int beginCol, int moveDist) {
        int endRow = beginRow - moveDist;
        return new ChessPosition(endRow, beginCol);
    }

    /**
     * @return coordinates after leftward move
     */
    public static ChessPosition left(int beginRow, int beginCol, int moveDist) {
        int endCol = beginCol - moveDist;
        return new ChessPosition(beginRow, endCol);
    }

    /**
     * @return coordinates after rightward move
     */
    public static ChessPosition right(int beginRow, int beginCol, int moveDist) {
        int endCol = beginCol + moveDist;
        return new ChessPosition(beginRow, endCol);
    }

    /**
     * @return coordinates after diagonal move
     */
    public static ChessPosition diagonal(int beginRow, int beginCol, int moveDist, int quad) {
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

    /**
     * @return coordinates after L Shaped move by Knight
     */
    public static ChessPosition LShape(int beginRow, int beginCol) {
        return new ChessPosition();
    }
}
