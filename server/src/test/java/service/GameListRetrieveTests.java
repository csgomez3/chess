package service;

import exception.*;
import service.result.GameFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
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
    @Order(1)
    public void successfulRetrieve() throws UnauthorizedException, ExistingGameException {
        gService.clearGames();
        gService.createGame(authToken, new CreationRequest("AvengersEndgame"));
        gService.createGame(authToken, new CreationRequest("KnightlyGame"));
        gService.createGame(authToken, new CreationRequest("Default"));
        gService.createGame(authToken, new CreationRequest("HammerTime"));
        gService.createGame(authToken, new CreationRequest("ICouldDoThisAllDay"));
        ArrayList<GameFormat> gameList = gService.getGameList(authToken).games();
        assertFalse(gameList.isEmpty());
    }

    @Test
    @Order(2)
    public void noNullRetrieve() {
        assertThrows(
                BadRequestException.class,
                () -> gService.getGameList(null)
        );
    }

    @Test
    @Order(3)
    public void unauthorizedRetrieve() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.getGameList("InvalidToken")
        );
    }

    @Test
    @Order(4)
    public void emptyList() throws UnauthorizedException {
        gService.clearGames();
        assertNotNull(gService.getGameList(authToken).games());
        assertTrue(gService.getGameList(authToken).games().isEmpty());
    }
}
