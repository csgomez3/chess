package service;

import exception.*;
import org.junit.jupiter.api.Order;
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
        CreationResult createResult = gService.createGame(player2AuthToken, new CreationRequest("lifesize"));
        gameID1 = createResult.gameID();
        CreationResult createResult2 = gService.createGame(player3AuthToken, new CreationRequest("portkey"));
        gameID2 = createResult2.gameID();
        CreationResult createResult3 = gService.createGame(player1AuthToken, new CreationRequest("lily"));
        gameID3 = createResult3.gameID();
        CreationResult createResult4 = gService.createGame(player1AuthToken, new CreationRequest("ughPotter"));
        gameID4 = createResult4.gameID();
        CreationResult createResult5 = gService.createGame(player2AuthToken, new CreationRequest("GryffindorWins"));
        gameID5 = createResult5.gameID();
        CreationResult createResult6 = gService.createGame(player3AuthToken, new CreationRequest("StolenIdentity"));
        gameID6 = createResult6.gameID();
    }

    @Test
    @Order(1)
    public void joinEmptyGame() throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        JoinRequest request = new JoinRequest("BLACK", gameID3);
        gService.joinGame(player1AuthToken, request);
    }

    @Test
    @Order(2)
    public void joinGameWithOnePlayer() throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        JoinRequest r = new JoinRequest("BLACK", gameID5);
        gService.joinGame(player1AuthToken, r);
        JoinRequest request = new JoinRequest("WHITE", gameID5);
        gService.joinGame(player2AuthToken, request);
    }

    @Test
    @Order(3)
    public void noUnauthorizedJoin() {
        assertThrows(
                UnauthorizedException.class,
                () -> gService.joinGame("NotValidAuthToken", new JoinRequest("WHITE", gameID2))
        );
    }

    @Test
    @Order(4)
    public void noNullJoin() {
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(null, new JoinRequest("WHITE", gameID2))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest(null, gameID2))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest("WHITE", 0))
        );
        assertThrows(
                BadRequestException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest("WHITE", 10000))
        );
    }

    @Test
    @Order(5)
    public void noJoiningGameThatDoesntExist() {
        assertThrows(
                NoGameFoundException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest("WHITE", 392))
        );
    }

    @Test
    @Order(6)
    public void noStealingWhite() throws UnauthorizedException, ColorTakenException, NoGameFoundException {
        JoinRequest request = new JoinRequest("WHITE", gameID1);
        gService.joinGame(player2AuthToken, request);
        assertThrows(
                ColorTakenException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest("WHITE", gameID1))
        );
    }

    @Test
    @Order(7)
    public void noStealingBlack() throws UnauthorizedException, ColorTakenException, NoGameFoundException {
        JoinRequest request = new JoinRequest("BLACK", gameID4);
        gService.joinGame(player1AuthToken, request);
        assertThrows(
                ColorTakenException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest("BLACK", gameID4))
        );
    }

    @Test
    @Order(8)
    public void noJoiningTwoPlayerGame() throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        JoinRequest r1 = new JoinRequest("BLACK", gameID6);
        gService.joinGame(player1AuthToken, r1);
        JoinRequest r2 = new JoinRequest("WHITE", gameID6);
        gService.joinGame(player2AuthToken, r2);
        assertThrows(
                ColorTakenException.class,
                () -> gService.joinGame(player3AuthToken, new JoinRequest("WHITE", gameID6))
        );
    }
}
