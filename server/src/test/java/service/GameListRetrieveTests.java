package service;

import exception.AlreadyTakenException;
import exception.ExistingGameException;
import exception.UnauthorizedException;
import model.GameData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import service.request.CreationRequest;
import service.request.RegisterRequest;
import service.result.RegisterResult;

import java.util.ArrayList;

public class GameListRetrieveTests {
    private static UserService uService;
    private static GameService gService;
    private static AuthService aService;
    private static String authToken;

    @BeforeAll
    public static void setup() throws AlreadyTakenException, ExistingGameException, UnauthorizedException {
        aService = new AuthService();
        uService = new UserService(aService);
        gService = new GameService(aService);
        RegisterResult result = uService.register(new RegisterRequest("dad", "hiimdad", "dad@eyeroll.com"));
        authToken = result.authToken();

        gService.createGame(new CreationRequest(authToken, "AvengersEndgame"));
        gService.createGame(new CreationRequest(authToken, "KnightlyGame"));
        gService.createGame(new CreationRequest(authToken, "Default"));
        gService.createGame(new CreationRequest(authToken, "HammerTime"));
        gService.createGame(new CreationRequest(authToken, "ICouldDoThisAllDay"));
    }

    @Test
    public void SuccessfulRetrieve() throws UnauthorizedException {
        ArrayList<GameData> gameList = gService.getGameList(authToken);
        assertFalse(gameList.isEmpty());
    }

    @Test
    public void NoNullRetrieve() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.getGameList(null)
        );
    }

    @Test
    public void UnauthorizedRetrieve() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.getGameList("InvalidToken")
        );
    }
}
