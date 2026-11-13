package service;

import exception.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import service.request.CreationRequest;
import service.request.JoinRequest;
import service.request.RegisterRequest;
import service.result.CreationResult;
import service.result.RegisterResult;

public class GameJoinTests {
    private static UserService uService;
    private static GameService gService;
    private static AuthService aService;
    private static String player1AuthToken;
    private static String player2AuthToken;
    private static String player3AuthToken;
    private static int gameID1;
    private static int gameID2;
    private static int gameID3;
    private static int gameID4;
    private static int gameID5;
    private static int gameID6;

    @BeforeAll
    public static void setup() throws AlreadyTakenException, ExistingGameException, UnauthorizedException {
        aService = new AuthService();
        uService = new UserService(aService);
        gService = new GameService(aService);
        RegisterResult regResult = uService.register(new RegisterRequest( "snape", "page394", "snape@hogwarts.edu"));
        player1AuthToken = regResult.authToken();
        RegisterResult regResult2 = uService.register(new RegisterRequest( "dumbledore", "alasearwax", "dumbledore@hogwarts.edu"));
        player2AuthToken = regResult2.authToken();
        RegisterResult regResult3 = uService.register(new RegisterRequest( "bartycrouch", "polyjuice", "moody@hogwarts.edu"));
        player3AuthToken = regResult3.authToken();
        CreationResult createResult = gService.createGame(new CreationRequest(player2AuthToken, "lifesize"));
        gameID1 = createResult.gameID();
        CreationResult createResult2 = gService.createGame(new CreationRequest(player3AuthToken, "portkey"));
        gameID2 = createResult2.gameID();
        CreationResult createResult3 = gService.createGame(new CreationRequest(player1AuthToken, "lily"));
        gameID3 = createResult3.gameID();
        CreationResult createResult4 = gService.createGame(new CreationRequest(player1AuthToken, "ughPotter"));
        gameID4 = createResult4.gameID();
        CreationResult createResult5 = gService.createGame(new CreationRequest(player2AuthToken, "GryffindorWins"));
        gameID5 = createResult5.gameID();
        CreationResult createResult6 = gService.createGame(new CreationRequest(player3AuthToken, "StolenIdentity"));
        gameID6 = createResult6.gameID();
    }

    @Test
    public void JoinEmptyGame() throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        JoinRequest request = new JoinRequest(player1AuthToken, "snape", "black", gameID3);
        gService.joinGame(request);
    }

    @Test
    public void JoinGameWithOnePlayer() throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        JoinRequest r = new JoinRequest(player1AuthToken, "snape", "black", gameID5);
        gService.joinGame(r);
        JoinRequest request = new JoinRequest(player2AuthToken, "dumbledore", "white", gameID5);
        gService.joinGame(request);
    }

    @Test
    public void NoUnauthorizedJoin() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.joinGame(new JoinRequest("NotValidAuthToken", "bartycrouch", "white", gameID2))
        );
    }

    @Test
    public void NoNullJoin() {
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(new JoinRequest(null, "bartycrouch", "white", gameID2))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, null, "white", gameID2))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", null, gameID2))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", "white", 0))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", "white", 10000))
        );
    }

    @Test
    public void NoJoiningGameThatDoesntExist() {
        assertThrows(
                NoGameFoundException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", "white", 392))
        );
    }

    @Test
    public void NoStealingWhite() throws UnauthorizedException, ColorTakenException, NoGameFoundException {
        JoinRequest request = new JoinRequest(player2AuthToken, "dumbledore", "white", gameID1);
        gService.joinGame(request);
        assertThrows(
                ColorTakenException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", "white", gameID1))
        );
    }

    @Test
    public void NoStealingBlack() throws UnauthorizedException, ColorTakenException, NoGameFoundException {
        JoinRequest request = new JoinRequest(player1AuthToken, "snape", "black", gameID4);
        gService.joinGame(request);
        assertThrows(
                ColorTakenException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", "black", gameID4))
        );
    }

    @Test
    public void NoJoiningTwoPlayerGame() throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        JoinRequest r1 = new JoinRequest(player1AuthToken, "snape", "black", gameID6);
        gService.joinGame(r1);
        JoinRequest r2 = new JoinRequest(player2AuthToken, "dumbledore", "white", gameID6);
        gService.joinGame(r2);
        assertThrows(
                ColorTakenException.class,
                () -> gService.joinGame(new JoinRequest(player3AuthToken, "bartycrouch", "white", gameID6))
        );
    }
}
