package service;

import exception.AlreadyTakenException;
import exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.result.RegisterResult;

import service.request.LogoutRequest;


public class UserLogoutTests {
    private static UserService service;
    private static String authToken;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        service = new UserService();
        RegisterResult result = service.register(new RegisterRequest("thanos", "ohSnap", "thanos@titan.com"));
        authToken = result.authToken();
    }

    @Test
    public void LegalLogout() throws UnauthorizedException {
        service.logout(new LogoutRequest(authToken));
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout(new LogoutRequest(authToken))
        );
    }
}
