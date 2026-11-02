package dataaccess;

import java.util.ArrayList;

import chess.ChessGame;
import model.GameData;

import exception.NoGameFoundException;

public class GameDAO {

    ArrayList<GameData> games = new ArrayList<GameData>();

    public void clearGames() {
        games.clear();
    }

    public GameData createGame(String gameName) {
        //DO NOT LEAVE BELOW THIS WAY
        int randomInt = 1;
        // DO NOT LEAVE ABOVE THIS WAY
        GameData newGame = new GameData(randomInt, null, null, gameName, new ChessGame());
        games.add(newGame);
        return newGame;
    }

    public GameData getGame(int GameID) {
        for (GameData game: games) {
            if (game.gameID() == GameID) {
                return game;
            }
        }
        throw new NoGameFoundException("Error: bad request: no game with that ID found");
    }

    public GameData getGame(String gameName) {
        for (GameData game: games) {
            if (game.gameName().equals(gameName)) {
                return game;
            }
        }
        return null;
    }

    public ArrayList<GameData> listGames() {
        return this.games;
    }

    public void updateGame() {

    }
}
