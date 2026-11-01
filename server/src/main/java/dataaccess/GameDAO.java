package dataaccess;

import java.util.ArrayList;

import model.GameData;

import exception.NoGameFoundException;

public class GameDAO {

    ArrayList<GameData> games = new ArrayList<GameData>();

    void clearGames() {
        games.clear();
    }

    boolean createGame() {

    }

    GameData getGame(int GameID) {
        for (GameData game: games) {
            if (game.gameID() == GameID) {
                return game;
            }
        }
        throw new NoGameFoundException("Error: bad request: no game with that ID found");
    }

    ArrayList<GameData> listGames() {
        return this.games;
    }

    boolean updateGame() {

    }
}
