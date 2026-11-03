package service.request;

import model.AuthData;

public record JoinRequest(String authToken, String username, String playerColor, int gameID) {
}
