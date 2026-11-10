package service;

import exception.AlreadyTakenException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.result.RegisterResult;

public class UserRegisterTests {

    private static UserService service;
    private static RegisterRequest existingUser;
    private static RegisterRequest illegalUser;
    private static RegisterRequest newUser;
    private static RegisterRequest anotherNewUser;
    private static RegisterResult result;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        service = new UserService();
        existingUser = new RegisterRequest("adamTheFirstMan", "adamWuzAProphet", "adam@garden.com");
        illegalUser = new RegisterRequest("adamTheFirstMan", "notTheFirstLivingMan", "adam@earth.com");
        newUser = new RegisterRequest("eveTheMotherOfAll", "itWasAFruitMaybeNotApple", "eve@garden.com");
        anotherNewUser = new RegisterRequest("noahTheProphet", "timeToStartOver", "noah@ark.com");
        result = service.register(existingUser);
    }

    @Test
    public void SuccessfulSignUp() throws AlreadyTakenException {
        assertEquals("adamTheFirstMan", result.username());
        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());
    }

    @Test
    public void UsernameTaken() throws AlreadyTakenException {
        assertThrows(
                AlreadyTakenException.class,
                () -> service.register(illegalUser)
        );
        assertEquals("adamTheFirstMan", result.username());
    }

    @Test
    public void DifferentUsernamesAllowed() throws AlreadyTakenException {
        RegisterResult result2 = service.register(newUser);
        RegisterResult result3 = service.register(anotherNewUser);

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
