package service;

import exception.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.request.LoginRequest;

import service.result.RegisterResult;

public class UserLoginTests {
    private static UserService service;
    private static AuthService aService;
    private static String correctPassword;

    @BeforeAll
    public static void init() throws AlreadyTakenException, UnauthorizedException {
        aService = new AuthService();
        service = new UserService(aService);
        correctPassword = "doobeedoobeedoobahdoo";
        RegisterResult regResult = service.register(new RegisterRequest("perryThePlatypus", correctPassword, "agentP@owca.org"));
        service.logout(regResult.authToken());
    }

    @Test
    @Order(1)
    public void loginExistingUser() throws NoUserFoundException, IncorrectPasswordException {
        RegisterResult result = service.login(new LoginRequest("perryThePlatypus", correctPassword));
        assertEquals("perryThePlatypus", result.username());
        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());
    }

    @Test
    @Order(2)
    public void noLoginForUnregisteredUser() {
        assertThrows(
                NoUserFoundException.class,
                () -> service.login(new LoginRequest("DrDoof", "selfDestructButton"))
        );
    }

    @Test
    @Order(3)
    public void noLoginForUnregisteredUserWithExistingPassword() {
        assertThrows(
                NoUserFoundException.class,
                () -> service.login(new LoginRequest("DrDoof", correctPassword))
        );
    }

    @Test
    @Order(4)
    public void noLoginForRegisteredUserWithWrongPassword() {
        assertThrows(
                IncorrectPasswordException.class,
                () -> service.login(new LoginRequest("perryThePlatypus", "selfDestructButton"))
        );
    }

    @Test
    @Order(5)
    public void noNullLogin() {
        assertThrows(
                BadRequestException.class,
                () -> service.login(new LoginRequest(null, null))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.login(new LoginRequest("username", null))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.login(new LoginRequest(null, "password"))
        );
    }
}
