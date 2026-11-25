package service;

import exception.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;
import service.result.RegisterResult;

public class UserRegisterTests {

    private static UserService service;
    private static AuthService aService;
    private static RegisterRequest illegalUser;
    private static RegisterRequest newUser;
    private static RegisterRequest anotherNewUser;
    private static RegisterResult result;

    @BeforeAll
    public static void setup() throws AlreadyTakenException {
        aService = new AuthService();
        service = new UserService(aService);
        RegisterRequest existingUser = new RegisterRequest("adamTheFirstMan", "adamWuzAProphet", "adam@garden.com");
        illegalUser = new RegisterRequest("adamTheFirstMan", "notTheFirstLivingMan", "adam@earth.com");
        newUser = new RegisterRequest("eveTheMotherOfAll", "itWasAFruitMaybeNotApple", "eve@garden.com");
        anotherNewUser = new RegisterRequest("noahTheProphet", "timeToStartOver", "noah@ark.com");
        result = service.register(existingUser);
    }

    @Test
    @Order(1)
    public void successfulSignUp() {
        assertEquals("adamTheFirstMan", result.username());
        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());
    }

    @Test
    @Order(2)
    public void usernameTaken() {
        assertThrows(
                AlreadyTakenException.class,
                () -> service.register(illegalUser)
        );
    }

    @Test
    @Order(3)
    public void differentUsernamesAllowed() throws AlreadyTakenException {
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

    @Test
    @Order(4)
    public void noNullRegister() {
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest(null, null, null))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest("username", null, null))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest(null, "password", null))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest(null, null, "email@web.com"))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest("username", "password", null))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest("username", null, "email@web.com"))
        );
        assertThrows(
                BadRequestException.class,
                () -> service.register(new RegisterRequest(null, "password", "email@web.com"))
        );
    }
}
