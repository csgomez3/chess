package service;

import exception.AlreadyTakenException;
import exception.ExistingGameException;
import exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import service.request.CreationRequest;
import service.request.RegisterRequest;
import service.result.RegisterResult;


public class GameClearTests {
    private static UserService uService;
    private static GameService gService;

    @BeforeAll
    public static void setup() throws UnauthorizedException, ExistingGameException, AlreadyTakenException {
        uService = new UserService();
        gService = new GameService();
        RegisterResult result = uService.register(new RegisterRequest("dad", "hiimdad", "dad@eyeroll.com"));
        String authToken = result.authToken();

        gService.createGame(new CreationRequest(authToken, "AvengersEndgame"));
        gService.createGame(new CreationRequest(authToken, "KnightlyGame"));
        gService.createGame(new CreationRequest(authToken, "Default"));
        gService.createGame(new CreationRequest(authToken, "HammerTime"));
        gService.createGame(new CreationRequest(authToken, "ICouldDoThisAllDay"));
    }

    @Test
    public void ClearAllGames() {
        gService.clearGames();
        assertTrue(gService.getListOfGames().isEmpty());
    }
}
