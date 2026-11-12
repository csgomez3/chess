package service;

import exception.AlreadyTakenException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import service.request.RegisterRequest;
import service.result.RegisterResult;

public class GameListRetrieveTests {
    private static UserService uService;
    private static GameService gService;
    private static String authToken;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        uService = new UserService();
        gService = new GameService();
        RegisterResult result = uService.register(new RegisterRequest("dad", "hiimdad", "dad@eyeroll.com"));
        authToken = result.authToken();
        //Example games created
    }
}
