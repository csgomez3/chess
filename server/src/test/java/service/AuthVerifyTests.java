package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthVerifyTests {
    private static AuthService service;
    private static String validToken1;
    private static String validToken2;
    private static String validToken3;
    private static String validToken4;
    private static String validToken5;

    @BeforeAll
    public static void setup() {
        service = new AuthService();
        validToken1 = service.createAuth("PerryThePlatypus");
        validToken2 = service.createAuth("DrDoof");
        validToken3 = service.createAuth("CaptainAmerica");
        validToken4 = service.createAuth("Threepio");
        validToken5 = service.createAuth("Frodo");
    }

    @Test
    public void VerifyExistingToken() {
        assertTrue(service.verifyAuth(validToken1));
        assertTrue(service.verifyAuth(validToken2));
        assertTrue(service.verifyAuth(validToken3));
        assertTrue(service.verifyAuth(validToken4));
        assertTrue(service.verifyAuth(validToken5));
    }

    @Test
    public void NullTokenReturnsFalse() {
        assertFalse(service.verifyAuth(null));
    }

    @Test
    public void InvalidTokenReturnsFalse() {
        assertFalse(service.verifyAuth("NotAValidToken"));
    }
}
