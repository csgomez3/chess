package service;

import exception.AlreadyTakenException;
import exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    public void LegalLogoutAndNoDoubleLogout() throws UnauthorizedException {
        service.logout(authToken);
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout(authToken)
        );
    }

    @Test
    public void NoNullTokenLogout() {
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout(null)
        );
    }

    @Test
    public void IllegalLogout() {
        assertThrows(
                UnauthorizedException.class,
                () -> service.logout("NotAValidToken")
        );
    }
}
