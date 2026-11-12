package service;

import exception.AlreadyTakenException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import service.request.RegisterRequest;
import service.result.RegisterResult;

public class GameJoinTests {
    private static UserService uService;
    private static GameService gService;
    private static String authToken;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        uService = new UserService();
        gService = new GameService();
        RegisterResult regResult = uService.register(new RegisterRequest( "snape", "page394", "snape@hogwarts.edu"));
        authToken = regResult.authToken();
    }
}
