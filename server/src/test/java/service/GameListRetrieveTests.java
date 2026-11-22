package service;

import exception.*;
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

        gService.createGame(authToken, new CreationRequest("AvengersEndgame"));
        gService.createGame(authToken, new CreationRequest("KnightlyGame"));
        gService.createGame(authToken, new CreationRequest("Default"));
        gService.createGame(authToken, new CreationRequest("HammerTime"));
        gService.createGame(authToken, new CreationRequest("ICouldDoThisAllDay"));
    }

    @Test
    public void SuccessfulRetrieve() throws UnauthorizedException {
        ArrayList<GameData> gameList = gService.getGameList(authToken);
        assertFalse(gameList.isEmpty());
    }

    @Test
    public void NoNullRetrieve() {
        assertThrows(
                BadRequestException.class,
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
