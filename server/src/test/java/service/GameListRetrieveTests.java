package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

public class GameListRetrieveTests {
    private static UserService uService;
    private static GameService gService;

    @BeforeAll
    public static void setup() {
        uService = new UserService();
        gService = new GameService();

    }
}
