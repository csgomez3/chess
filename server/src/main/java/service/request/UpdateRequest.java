package service.request;

import chess.ChessGame;

public record UpdateRequest(String username, ChessGame.TeamColor team) {
}
