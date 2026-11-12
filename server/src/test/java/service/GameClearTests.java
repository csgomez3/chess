package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;


public class GameClearTests {
    private static GameService service;

    @BeforeAll
    public static void setup() {
        service = new GameService();
        //Create games
    }

    @Test
    public void ClearAllGames() {
        service.clearGames();
        assertTrue(service.getListOfGames().isEmpty());
    }
}
