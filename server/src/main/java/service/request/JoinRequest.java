package service.request;

public record JoinRequest(String authToken, String username, String color, int gameID) {
}
