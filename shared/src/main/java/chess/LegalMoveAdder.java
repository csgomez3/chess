package chess;

import java.util.List;

public class LegalMoveAdder {
    //Move adders
    public static void northPathAdder(ChessPosition beginPos, int moveDist,
                                      ChessBoard board, ChessPiece piece, List<ChessMove> list) {
        boolean pathClearNorth = true;
        int pathDist = 1;
        ChessPosition endPos;
        while (pathDist <= moveDist){
            if (beginPos.getRow() <= 8 - pathDist && pathClearNorth) {
                endPos = LegalMoveLibrary.north(beginPos, pathDist);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearNorth = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearNorth = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            pathDist++;
        }
    }

    public static void southPathAdder(ChessPosition beginPos, int moveDist,
                                      ChessBoard board, ChessPiece piece, List<ChessMove> list) {
        boolean pathClearSouth = true;
        int pathDist = 1;
        ChessPosition endPos;
        while (pathDist <= moveDist) {
            if (beginPos.getRow() > pathDist && pathClearSouth) {
                endPos = LegalMoveLibrary.south(beginPos, pathDist);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearSouth = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearSouth = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            pathDist++;
        }
    }

    public static void westPathAdder(ChessPosition beginPos, int moveDist,
                                     ChessBoard board, ChessPiece piece, List<ChessMove> list) {
        boolean pathClearWest = true;
        int pathDist = 1;
        ChessPosition endPos;
        while (pathDist <= moveDist) {
            if (beginPos.getColumn() > pathDist && pathClearWest) {
                endPos = LegalMoveLibrary.west(beginPos, pathDist);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearWest = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearWest = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            pathDist++;
        }
    }

    public static void eastPathAdder(ChessPosition beginPos, int moveDist,
                                     ChessBoard board, ChessPiece piece, List<ChessMove> list) {
        boolean pathClearEast = true;
        int pathDist = 1;
        ChessPosition endPos;
        while (pathDist <= moveDist) {
            if (beginPos.getColumn() <= 8 - pathDist && pathClearEast) {
                endPos = LegalMoveLibrary.east(beginPos, pathDist);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearEast = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearEast = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            pathDist++;
        }
    }

    public static void diagPathAdder(ChessPosition beginPos, int moveDist,
                                     ChessBoard board, ChessPiece piece, List<ChessMove> list) {
        boolean pathClearNE = true;
        boolean pathClearNW = true;
        boolean pathClearSW = true;
        boolean pathClearSE = true;
        int pathDist = 1;
        ChessPosition endPos;
        while (pathDist <= moveDist) {
            if (beginPos.getRow() <= 8 - pathDist && beginPos.getColumn() <= 8 - pathDist && pathClearNE) {
                endPos = LegalMoveLibrary.diagonal(beginPos, pathDist, LegalMoveLibrary.DiagDir.NE);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearNE = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearNE = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            if (beginPos.getRow() <= 8 - pathDist && beginPos.getColumn() > pathDist && pathClearNW) {
                endPos = LegalMoveLibrary.diagonal(beginPos, pathDist, LegalMoveLibrary.DiagDir.NW);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearNW = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearNW = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            if (beginPos.getRow() > pathDist && beginPos.getColumn() > pathDist && pathClearSW) {
                endPos = LegalMoveLibrary.diagonal(beginPos, pathDist, LegalMoveLibrary.DiagDir.SW);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearSW = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearSW = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            if (beginPos.getRow() > pathDist && beginPos.getColumn() <= 8 - pathDist && pathClearSE) {
                endPos = LegalMoveLibrary.diagonal(beginPos, pathDist, LegalMoveLibrary.DiagDir.SE);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == piece.getTeamColor()) {
                    pathClearSE = false;
                } else {
                    if (board.getPiece(endPos) != null) {
                        pathClearSE = false;
                    }
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            pathDist++;
        }
    }

    public static void LPathAdder(ChessPosition beginPos, ChessBoard board,
                                  ChessPiece piece, List<ChessMove> list) {
        ChessPosition endPos;
        if (beginPos.getRow() <= 8 - 2 && beginPos.getColumn() <= 8 - 1) {
            endPos = LegalMoveLibrary.LShape(beginPos, 2, LegalMoveLibrary.DiagDir.NE);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() <= 8 - 1 && beginPos.getColumn() <= 8 - 2) {
            endPos = LegalMoveLibrary.LShape(beginPos, 1, LegalMoveLibrary.DiagDir.NE);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() <= 8 - 1 && beginPos.getColumn() > 2) {
            endPos = LegalMoveLibrary.LShape(beginPos, 1, LegalMoveLibrary.DiagDir.NW);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() <= 8 - 2 && beginPos.getColumn() > 1) {
            endPos = LegalMoveLibrary.LShape(beginPos, 2, LegalMoveLibrary.DiagDir.NW);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() > 2 && beginPos.getColumn() > 1) {
            endPos = LegalMoveLibrary.LShape(beginPos, 2, LegalMoveLibrary.DiagDir.SW);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() > 1 && beginPos.getColumn() > 2) {
            endPos = LegalMoveLibrary.LShape(beginPos, 1, LegalMoveLibrary.DiagDir.SW);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() > 1 && beginPos.getColumn() <= 8 - 2) {
            endPos = LegalMoveLibrary.LShape(beginPos, 1, LegalMoveLibrary.DiagDir.SE);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
        if (beginPos.getRow() > 2 && beginPos.getColumn() <= 8 - 1) {
            endPos = LegalMoveLibrary.LShape(beginPos, 2, LegalMoveLibrary.DiagDir.SE);
            if (board.getPiece(endPos) == null ||
                    board.getPiece(endPos).getTeamColor() != piece.getTeamColor()) {
                list.add(new ChessMove(beginPos, endPos, null));
            }
        }
    }

    public static void pawnPathAdder(ChessPosition beginPos, ChessBoard board,
                                     ChessPiece piece, List<ChessMove> list) {
        ChessPosition endPos;
        boolean pathClearNorth = true;
        boolean pathClearSouth = true;
        //WHITE PAWN
        if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            //Standard
            if (beginPos.getRow() < 7) {
                endPos = LegalMoveLibrary.north(beginPos, 1);
                if (board.getPiece(endPos) == null) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
                else {
                    pathClearNorth = false;
                }
            }
            //Capture NE
            if (beginPos.getRow() < 7 && beginPos.getColumn() < 8) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.NE);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            //Capture NW
            if (beginPos.getRow() < 7 && beginPos.getColumn() > 1) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.NW);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            //Jump on first turn
            if (beginPos.getRow() == 2) {
                endPos = LegalMoveLibrary.north(beginPos, 2);
                if (board.getPiece(endPos) == null && pathClearNorth) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            //Standard promote
            if (beginPos.getRow() == 7) {
                endPos = LegalMoveLibrary.north(beginPos, 1);
                if (board.getPiece(endPos) == null) {
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.QUEEN));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.BISHOP));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.KNIGHT));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.ROOK));
                }
            }
            //Capture NE and promote
            if (beginPos.getRow() == 7 && beginPos.getColumn() < 8) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.NE);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.QUEEN));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.BISHOP));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.KNIGHT));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.ROOK));
                }
            }
            //Capture NW and promote
            if (beginPos.getRow() == 7 && beginPos.getColumn() > 1) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.NW);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.QUEEN));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.BISHOP));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.KNIGHT));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.ROOK));
                }
            }
        }
        //BLACK PAWN
        if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            //Standard
            if (beginPos.getRow() > 2) {
                endPos = LegalMoveLibrary.south(beginPos, 1);
                if (board.getPiece(endPos) == null) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
                else {
                    pathClearSouth = false;
                }
            }
            //Capture SW
            if (beginPos.getRow() > 2 && beginPos.getColumn() > 1) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.SW);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            //Capture SE
            if (beginPos.getRow() > 2 && beginPos.getColumn() < 8) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.SE);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            //Jump on first turn
            if (beginPos.getRow() == 7) {
                endPos = LegalMoveLibrary.south(beginPos, 2);
                if (board.getPiece(endPos) == null && pathClearSouth) {
                    list.add(new ChessMove(beginPos, endPos, null));
                }
            }
            //Standard promote
            if (beginPos.getRow() == 2) {
                endPos = LegalMoveLibrary.south(beginPos, 1);
                if (board.getPiece(endPos) == null) {
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.QUEEN));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.BISHOP));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.KNIGHT));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.ROOK));
                }
            }
            //Capture SW and promote
            if (beginPos.getRow() == 2 && beginPos.getColumn() > 1) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.SW);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.QUEEN));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.BISHOP));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.KNIGHT));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.ROOK));
                }
            }
            //Capture SE and promote
            if (beginPos.getRow() == 2 && beginPos.getColumn() < 8) {
                endPos = LegalMoveLibrary.diagonal(beginPos, 1, LegalMoveLibrary.DiagDir.SE);
                if (board.getPiece(endPos) != null &&
                        board.getPiece(endPos).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.QUEEN));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.BISHOP));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.KNIGHT));
                    list.add(new ChessMove(beginPos, endPos, ChessPiece.PieceType.ROOK));
                }
            }
        }
    }
}
