package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthClearTests {
    private static AuthService service;

    @BeforeAll
    public static void setup() {
        service = new AuthService();
        service.createAuth("PerryThePlatypus");
        service.createAuth("DrDoof");
        service.createAuth("CaptainAmerica");
        service.createAuth("Threepio");
        service.createAuth("Frodo");
    }

    @Test
    public void ClearAllAuthTokens() {
        service.clearAuthData();
        assertTrue(service.getAuthList().isEmpty());
    }
}
