package service;

import exception.AlreadyTakenException;
import exception.IncorrectPasswordException;
import exception.NoUserFoundException;
import exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.request.LoginRequest;
import service.request.LogoutRequest;

import service.result.RegisterResult;

public class UserLoginTests {
    private static UserService service;
    private static String correctPassword;

    @BeforeAll
    public static void init() throws AlreadyTakenException, UnauthorizedException {
        service = new UserService();
        correctPassword = "doobeedoobeedoobahdoo";
        RegisterResult regResult = service.register(new RegisterRequest("perryThePlatypus", correctPassword, "agentP@owca.org"));
        service.logout(new LogoutRequest(regResult.authToken()));
    }

    @Test
    public void LoginExistingUser() throws NoUserFoundException, IncorrectPasswordException {
        RegisterResult result = service.login(new LoginRequest("perryThePlatypus", correctPassword));
        assertEquals("perryThePlatypus", result.username());
        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());
    }

    @Test
    public void NoLoginForUnregisteredUser() {
        assertThrows(
                NoUserFoundException.class,
                () -> service.login(new LoginRequest("DrDoof", "selfDestructButton"))
        );
    }

    @Test
    public void NoLoginForUnregisteredUserWithExistingPassword() {
        assertThrows(
                NoUserFoundException.class,
                () -> service.login(new LoginRequest("DrDoof", correctPassword))
        );
    }

    @Test
    public void NoLoginForRegisteredUserWithWrongPassword() {
        assertThrows(
                IncorrectPasswordException.class,
                () -> service.login(new LoginRequest("perryThePlatypus", "selfDestructButton"))
        );
    }
}
