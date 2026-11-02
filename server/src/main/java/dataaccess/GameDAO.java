package dataaccess;

import java.util.ArrayList;

import model.GameData;

import exception.NoGameFoundException;

public class GameDAO {

    ArrayList<GameData> games = new ArrayList<GameData>();

    public void clearGames() {
        games.clear();
    }

    public GameData createGame() {

    }

    public GameData getGame(int GameID) {
        for (GameData game: games) {
            if (game.gameID() == GameID) {
                return game;
            }
        }
        throw new NoGameFoundException("Error: bad request: no game with that ID found");
    }

    public ArrayList<GameData> listGames() {
        return this.games;
    }

    public void updateGame() {

    }
}
