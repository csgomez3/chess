package service;

import exception.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.result.RegisterResult;


public class UserLogoutTests {
    private static UserService service;
    private static AuthService aService;
    private static String authToken;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        aService = new AuthService();
        service = new UserService(aService);
        RegisterResult result = service.register(new RegisterRequest("thanos", "ohSnap", "thanos@titan.com"));
        authToken = result.authToken();
    }

    @Test
    @Order(1)
    public void legalLogoutAndNoDoubleLogout() throws UnauthorizedException {
        service.logout(authToken);
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout(authToken)
        );
    }

    @Test
    @Order(2)
    public void noNullTokenLogout() {
        assertThrows(
                BadRequestException.class,
                () -> service.logout(null)
        );
    }

    @Test
    @Order(3)
    public void illegalLogout() {
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout("NotAValidToken")
        );
    }
}
