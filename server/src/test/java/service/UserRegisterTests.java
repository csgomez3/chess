package service;

import exception.AlreadyTakenException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.result.RegisterResult;

public class UserRegisterTests {
    @Test
    public void SuccessfulSignUp() throws AlreadyTakenException {
        UserService service = new UserService();

        RegisterResult result = service.register(new RegisterRequest("adamTheFirstMan", "adamWuzAProphet", "adam@garden.com"));

        assertEquals("adamTheFirstMan", result.username());
        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());
    }

    @Test
    public void UsernameTaken() throws AlreadyTakenException {
        UserService service = new UserService();

        RegisterResult result = service.register(new RegisterRequest("adamTheFirstMan", "adamWuzAProphet", "adam@garden.com"));
        assertThrows(
                AlreadyTakenException.class,
                () -> service.register(new RegisterRequest("adamTheFirstMan", "notTheFirstLivingMan", "adam@earth.com"))
        );
        assertEquals("adamTheFirstMan", result.username());
    }

    @Test
    public void DifferentUsernamesAllowed() throws AlreadyTakenException {
        UserService service = new UserService();

        RegisterResult result = service.register(new RegisterRequest("adamTheFirstMan", "adamWuzAProphet", "adam@garden.com"));
        RegisterResult result2 = service.register(new RegisterRequest("eveTheMotherOfAll", "itWasAFruitMaybeNotApple", "eve@garden.com"));
        RegisterResult result3 = service.register(new RegisterRequest("noahTheProphet", "timeToStartOver", "noah@ark.com"));

        assertEquals("adamTheFirstMan", result.username());
        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());
        assertEquals("eveTheMotherOfAll", result2.username());
        assertNotNull(result2.authToken());
        assertFalse(result2.authToken().isBlank());
        assertEquals("noahTheProphet", result3.username());
        assertNotNull(result3.authToken());
        assertFalse(result3.authToken().isBlank());
    }
}
