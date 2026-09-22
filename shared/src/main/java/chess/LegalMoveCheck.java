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
    public static Collection<ChessMove> legalMove(ChessPiece piece, ChessPosition position, ChessBoard board) {
        int beginRow = position.getRow();
        int beginCol = position.getColumn();
        ChessPiece.PieceType type = piece.getPieceType();

        int moveDist = 1;
        boolean pathClearForward = true;
        boolean pathClearBackward = true;
        boolean pathClearLeft = true;
        boolean pathClearRight = true;
        boolean pathClearDiag1 = true;
        boolean pathClearDiag2 = true;
        boolean pathClearDiag3 = true;
        boolean pathClearDiag4 = true;
        ChessPosition endPos;
        List<ChessMove> legalMoveList = new ArrayList<>();

        switch (type) {
            case KING:
                if (beginRow < 8) {
                    endPos = north(beginRow, beginCol, moveDist);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 1) {
                    endPos = south(beginRow, beginCol, moveDist);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginCol > 1) {
                    endPos = west(beginRow, beginCol, moveDist);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginCol < 8) {
                    endPos = east(beginRow, beginCol, moveDist);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow < 8 && beginCol < 8) {
                    endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NE);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow < 8 && beginCol > 1) {
                    endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NW);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 1 && beginCol > 1) {
                    endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SW);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 1 && beginCol < 8) {
                    endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SE);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                break;
            case QUEEN:
                while (moveDist < 8) {
                    if (beginRow <= 8 - moveDist && pathClearForward) {
                        endPos = north(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearForward = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearForward = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > moveDist && pathClearBackward) {
                        endPos = south(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearBackward = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearBackward = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginCol > moveDist && pathClearLeft) {
                        endPos = west(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearLeft = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearLeft = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginCol <= 8 - moveDist && pathClearRight) {
                        endPos = east(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearRight = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearRight = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow <= 8 - moveDist && beginCol <= 8 - moveDist && pathClearDiag1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag1 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag1 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow <= 8 - moveDist && beginCol > moveDist && pathClearDiag2) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag2 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag2 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > moveDist && beginCol > moveDist && pathClearDiag3) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag3 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag3 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > moveDist && beginCol <= 8 - moveDist && pathClearDiag4) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag4 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag4 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    moveDist++;
                }
                break;
            case BISHOP:
                while (moveDist < 8) {
                    if (beginRow <= 8 - moveDist && beginCol <= 8 - moveDist && pathClearDiag1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag1 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag1 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow <= 8 - moveDist && beginCol > moveDist && pathClearDiag2) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag2 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag2 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > moveDist && beginCol > moveDist && pathClearDiag3) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag3 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag3 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > moveDist && beginCol <= 8 - moveDist && pathClearDiag4) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearDiag4 = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearDiag4 = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    moveDist++;
                }
                break;
            case KNIGHT:
                if (beginRow <= 8 - 2 && beginCol <= 8 - 1) {
                    endPos = LShape(beginRow, beginCol, 2, DiagDir.NE);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow <= 8 - 1 && beginCol <= 8 - 2) {
                    endPos = LShape(beginRow, beginCol, 1, DiagDir.NE);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow <= 8 - 1 && beginCol > 2) {
                    endPos = LShape(beginRow, beginCol, 1, DiagDir.NW);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow <= 8 - 2 && beginCol > 1) {
                    endPos = LShape(beginRow, beginCol, 2, DiagDir.NW);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 2 && beginCol > 1) {
                    endPos = LShape(beginRow, beginCol, 2, DiagDir.SW);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 1 && beginCol > 2) {
                    endPos = LShape(beginRow, beginCol, 1, DiagDir.SW);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 1 && beginCol <= 8 - 2) {
                    endPos = LShape(beginRow, beginCol, 1, DiagDir.SE);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                if (beginRow > 2 && beginCol <= 8 - 1) {
                    endPos = LShape(beginRow, beginCol, 2, DiagDir.SE);
                    if (board.getPiece(endPos) == null ||
                            board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                        legalMoveList.add(new ChessMove(position, endPos, null));
                    }
                }
                break;
            case ROOK:
                while (moveDist < 8) {
                    if (beginRow <= 8 - moveDist && pathClearForward) {
                        endPos = north(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearForward = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearForward = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > moveDist && pathClearBackward) {
                        endPos = south(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearBackward = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearBackward = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginCol > moveDist && pathClearLeft) {
                        endPos = west(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearLeft = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearLeft = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginCol <= 8 - moveDist && pathClearRight) {
                        endPos = east(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                            pathClearRight = false;
                        } else {
                            if (board.getPiece(endPos) != null) {
                                pathClearRight = false;
                            }
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    moveDist++;
                }
                break;
            case PAWN:
                //WHITE PAWN
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (beginRow < 7) {
                        endPos = north(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) == null) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow < 7 && beginCol < 8) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow < 7 && beginCol > 1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow == 2) {
                        endPos = north(beginRow, beginCol, 2);
                        if (board.getPiece(endPos) == null &&
                                board.getPiece(north(beginRow, beginCol, 1)) == null) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow == 7) {
                        endPos = north(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) == null) {
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.QUEEN));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.BISHOP));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.KNIGHT));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.ROOK));
                        }
                    }
                    if (beginRow == 7 && beginCol < 8) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.QUEEN));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.BISHOP));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.KNIGHT));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.ROOK));
                        }
                    }
                    if (beginRow == 7 && beginCol > 1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.NW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.QUEEN));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.BISHOP));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.KNIGHT));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.ROOK));
                        }
                    }
                }
                //BLACK PAWN
                if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (beginRow > 2) {
                        endPos = south(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) == null) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > 2 && beginCol > 1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow > 2 && beginCol < 8) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow == 7) {
                        endPos = south(beginRow, beginCol, 2);
                        if (board.getPiece(endPos) == null &&
                                board.getPiece(south(beginRow, beginCol, 1)) == null) {
                            legalMoveList.add(new ChessMove(position, endPos, null));
                        }
                    }
                    if (beginRow == 2) {
                        endPos = south(beginRow, beginCol, moveDist);
                        if (board.getPiece(endPos) == null) {
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.QUEEN));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.BISHOP));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.KNIGHT));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.ROOK));
                        }
                    }
                    if (beginRow == 2 && beginCol > 1) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SW);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.QUEEN));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.BISHOP));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.KNIGHT));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.ROOK));
                        }
                    }
                    if (beginRow == 2 && beginCol < 8) {
                        endPos = diagonal(beginRow, beginCol, moveDist, DiagDir.SE);
                        if (board.getPiece(endPos) != null &&
                                board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.QUEEN));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.BISHOP));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.KNIGHT));
                            legalMoveList.add(new ChessMove(position, endPos, ChessPiece.PieceType.ROOK));
                        }
                    }
                }
                break;
        }
        return legalMoveList;
    }

    public enum DiagDir {
        NE,
        NW,
        SW,
        SE
    }

    //Move types

    /**
     * @return coordinates after forward move
     */
    public static ChessPosition north(int beginRow, int beginCol, int moveDist) {
        int endRow = beginRow + moveDist;
        return new ChessPosition(endRow, beginCol);
    }

    /**
     * @return coordinates after backward move
     */
    public static ChessPosition south(int beginRow, int beginCol, int moveDist) {
        int endRow = beginRow - moveDist;
        return new ChessPosition(endRow, beginCol);
    }

    /**
     * @return coordinates after leftward move
     */
    public static ChessPosition west(int beginRow, int beginCol, int moveDist) {
        int endCol = beginCol - moveDist;
        return new ChessPosition(beginRow, endCol);
    }

    /**
     * @return coordinates after rightward move
     */
    public static ChessPosition east(int beginRow, int beginCol, int moveDist) {
        int endCol = beginCol + moveDist;
        return new ChessPosition(beginRow, endCol);
    }

    /**
     * @return coordinates after diagonal move
     */
    public static ChessPosition diagonal(int beginRow, int beginCol, int moveDist, DiagDir dir) {
        int endRow;
        int endCol;

        if (dir == DiagDir.NE) {
            endRow = beginRow + moveDist;
            endCol = beginCol + moveDist;
        }
        else if (dir == DiagDir.NW) {
            endRow = beginRow + moveDist;
            endCol = beginCol - moveDist;
        }
        else if (dir == DiagDir.SW) {
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
    public static ChessPosition LShape(int beginRow, int beginCol, int rowDist, DiagDir dir) {
        int endRow;
        int endCol;

        if (dir == DiagDir.NE) {
            if (rowDist == 2) {
                endRow = beginRow + 2;
                endCol = beginCol + 1;
            }
            else {
                endRow = beginRow + 1;
                endCol = beginCol + 2;
            }
        }
        else if (dir == DiagDir.NW) {
            if (rowDist == 2) {
                endRow = beginRow + 2;
                endCol = beginCol - 1;
            }
            else {
                endRow = beginRow + 1;
                endCol = beginCol - 2;
            }
        }
        else if (dir == DiagDir.SW) {
            if (rowDist == 2) {
                endRow = beginRow - 2;
                endCol = beginCol - 1;
            }
            else {
                endRow = beginRow - 1;
                endCol = beginCol - 2;
            }
        }
        else {
            if (rowDist == 2) {
                endRow = beginRow - 2;
                endCol = beginCol + 1;
            }
            else {
                endRow = beginRow - 1;
                endCol = beginCol + 2;
            }
        }
        return new ChessPosition(endRow, endCol);
    }
}
