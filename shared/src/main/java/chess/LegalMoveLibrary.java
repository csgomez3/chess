package chess;

/**
 * make sure you add notes about what this class is
 */
public class LegalMoveLibrary {

    public enum DiagDir {
        NE,
        NW,
        SW,
        SE
    }

    /**
     * @return coordinates after forward move
     */
    public static ChessPosition north(ChessPosition beginPos, int moveDist) {
        int endRow = beginPos.getRow() + moveDist;
        return new ChessPosition(endRow, beginPos.getColumn());
    }

    /**
     * @return coordinates after backward move
     */
    public static ChessPosition south(ChessPosition beginPos, int moveDist) {
        int endRow = beginPos.getRow() - moveDist;
        return new ChessPosition(endRow, beginPos.getColumn());
    }

    /**
     * @return coordinates after leftward move
     */
    public static ChessPosition west(ChessPosition beginPos, int moveDist) {
        int endCol = beginPos.getColumn() - moveDist;
        return new ChessPosition(beginPos.getRow(), endCol);
    }

    /**
     * @return coordinates after rightward move
     */
    public static ChessPosition east(ChessPosition beginPos, int moveDist) {
        int endCol = beginPos.getColumn() + moveDist;
        return new ChessPosition(beginPos.getRow(), endCol);
    }

    /**
     * @return coordinates after diagonal move
     */
    public static ChessPosition diagonal(ChessPosition beginPos, int moveDist, LegalMoveLibrary.DiagDir dir) {
        int endRow;
        int endCol;

        if (dir == LegalMoveLibrary.DiagDir.NE) {
            endRow = beginPos.getRow() + moveDist;
            endCol = beginPos.getColumn() + moveDist;
        }
        else if (dir == LegalMoveLibrary.DiagDir.NW) {
            endRow = beginPos.getRow() + moveDist;
            endCol = beginPos.getColumn() - moveDist;
        }
        else if (dir == LegalMoveLibrary.DiagDir.SW) {
            endRow = beginPos.getRow() - moveDist;
            endCol = beginPos.getColumn() - moveDist;
        }
        else {
            endRow = beginPos.getRow() - moveDist;
            endCol = beginPos.getColumn() + moveDist;
        }

        return new ChessPosition(endRow, endCol);
    }

    /**
     * @return coordinates after L Shaped move by Knight
     */
    public static ChessPosition LShape(ChessPosition beginPos, int rowDist, LegalMoveLibrary.DiagDir dir) {
        int endRow;
        int endCol;

        if (dir == LegalMoveLibrary.DiagDir.NE) {
            if (rowDist == 2) {
                endRow = beginPos.getRow() + 2;
                endCol = beginPos.getColumn() + 1;
            }
            else {
                endRow = beginPos.getRow() + 1;
                endCol = beginPos.getColumn() + 2;
            }
        }
        else if (dir == LegalMoveLibrary.DiagDir.NW) {
            if (rowDist == 2) {
                endRow = beginPos.getRow() + 2;
                endCol = beginPos.getColumn() - 1;
            }
            else {
                endRow = beginPos.getRow() + 1;
                endCol = beginPos.getColumn() - 2;
            }
        }
        else if (dir == LegalMoveLibrary.DiagDir.SW) {
            if (rowDist == 2) {
                endRow = beginPos.getRow() - 2;
                endCol = beginPos.getColumn() - 1;
            }
            else {
                endRow = beginPos.getRow() - 1;
                endCol = beginPos.getColumn() - 2;
            }
        }
        else {
            if (rowDist == 2) {
                endRow = beginPos.getRow() - 2;
                endCol = beginPos.getColumn() + 1;
            }
            else {
                endRow = beginPos.getRow() - 1;
                endCol = beginPos.getColumn() + 2;
            }
        }
        return new ChessPosition(endRow, endCol);
    }
}
