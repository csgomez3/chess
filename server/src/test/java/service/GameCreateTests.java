package service;

import exception.AlreadyTakenException;
import exception.BadRequestException;
import exception.ExistingGameException;
import exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import service.request.CreationRequest;
import service.request.RegisterRequest;
import service.result.CreationResult;
import service.result.RegisterResult;

public class GameCreateTests {
    private static UserService uService;
    private static GameService gService;
    private static String authToken;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        uService = new UserService();
        gService = new GameService();
        RegisterResult regResult = uService.register(new RegisterRequest("optimusPrime", "autobotsrollout", "optimus@prime.com"));
        authToken = regResult.authToken();
    }

    @Test
    public void SuccessfulGameCreation() throws UnauthorizedException, ExistingGameException {
        CreationResult result = gService.createGame(new CreationRequest(authToken, "ValidName"));
        assertTrue(result.gameID() >= 1);
        assertTrue(result.gameID() < 10000);
    }

    @Test
    public void SuccessfulGameCreationOfMultipleGames() throws UnauthorizedException, ExistingGameException {
        CreationResult result = gService.createGame(new CreationRequest(authToken, "ValidName"));
        CreationResult result2 = gService.createGame(new CreationRequest(authToken, "UniqueName"));
        CreationResult result3 = gService.createGame(new CreationRequest(authToken, "GameOfChess"));
        CreationResult result4 = gService.createGame(new CreationRequest(authToken, "ChessGame"));
        CreationResult result5 = gService.createGame(new CreationRequest(authToken, "AvengersEndgame"));
        assertTrue(result.gameID() >= 1);
        assertTrue(result.gameID() < 10000);
        assertTrue(result2.gameID() >= 1);
        assertTrue(result2.gameID() < 10000);
        assertTrue(result3.gameID() >= 1);
        assertTrue(result3.gameID() < 10000);
        assertTrue(result4.gameID() >= 1);
        assertTrue(result4.gameID() < 10000);
        assertTrue(result5.gameID() >= 1);
        assertTrue(result5.gameID() < 10000);
        assertNotEquals(result.gameID(), result2.gameID());
        assertNotEquals(result.gameID(), result3.gameID());
        assertNotEquals(result.gameID(), result4.gameID());
        assertNotEquals(result.gameID(), result5.gameID());
        assertNotEquals(result2.gameID(), result3.gameID());
        assertNotEquals(result2.gameID(), result4.gameID());
        assertNotEquals(result2.gameID(), result5.gameID());
        assertNotEquals(result3.gameID(), result4.gameID());
        assertNotEquals(result3.gameID(), result5.gameID());
        assertNotEquals(result4.gameID(), result5.gameID());
    }

    @Test
    public void NoUnauthorizedGameCreation() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.createGame(new CreationRequest("InvalidToken", "ValidName"))
        );
    }

    @Test
    public void NoNullGameCreation() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.createGame(new CreationRequest(null, "ValidName"))
        );
    }

    @Test
    public void NameRequiredForCreation() {
        assertThrows(
                BadRequestException.class,
                () -> gService.createGame(new CreationRequest(authToken, null))
        );
    }

    @Test
    public void NoDuplicateGames() throws UnauthorizedException, ExistingGameException {
        gService.createGame(new CreationRequest(authToken, "ValidName"));
        assertThrows(
                ExistingGameException.class,
                () -> gService.createGame(new CreationRequest(authToken, "ValidName"))
        );
    }
}
