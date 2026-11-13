package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthDeleteTests {
    private static AuthService service;
    private static String validAuthToken;

    @BeforeAll
    public static void setup() {
        service = new AuthService();
        validAuthToken = service.createAuth("HeyFerb");
    }

    @Test
    public void DeleteExistingEntryAndNoDoubleDelete() {
        assertTrue(service.deleteAuth(validAuthToken));
        assertFalse(service.deleteAuth(validAuthToken));
    }

    @Test
    public void NullTokenReturnsFalse() {
        assertFalse(service.deleteAuth(null));
    }

    @Test
    public void InvalidTokenReturnsFalse() {
        assertFalse(service.deleteAuth("NotAValidToken"));
    }
}
