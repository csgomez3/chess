package service;

import exception.AlreadyTakenException;
import exception.BadRequestException;
import exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.result.RegisterResult;


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
        service.logout(authToken);
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout(authToken)
        );
    }

    @Test
    public void NoNullTokenLogout() {
        assertThrows(
                BadRequestException.class,
                () -> service.logout(null)
        );
    }

    @Test
    public void IllegalLogout() {
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout("ThisIsNotAValidAuthToken")
        );
    }
}
