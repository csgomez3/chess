package service;

import exception.AlreadyTakenException;
import exception.BadRequestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import service.request.RegisterRequest;

public class UserClearTests {
    private static UserService service;

    @BeforeAll
    public static void setup() throws AlreadyTakenException, BadRequestException {
        service = new UserService();
        service.register(new RegisterRequest("PerryThePlatypus", "notdoinmuch", "agentp@owca.org"));
        service.register(new RegisterRequest("DrDoof", "selfdestructbutton", "doof@lovemuffin.com"));
        service.register(new RegisterRequest("CaptainAmerica", "takeallthestupidwithyou", "steverogers@avengers.gov"));
        service.register(new RegisterRequest("Threepio", "thankthemaker", "C3P0@rebels.com"));
        service.register(new RegisterRequest("Frodo", "melon", "frodo@shire.com"));
    }

    @Test
    public void ClearAllUsersAndTokens() {
        service.clearUserData();
        assertTrue(service.getUserList().isEmpty());
        assertTrue(service.getAuthList().isEmpty());
    }
}
