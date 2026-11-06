package dataaccess;

import java.util.ArrayList;

import chess.ChessGame;
import exception.ColorTakenException;
import model.GameData;

import exception.*;

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
        return null;
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

    public boolean updateGame(int gameID, String username, String team) {
        GameData gameToJoin = getGame(gameID);
        String desiredColor = team.toUpperCase();
        if (desiredColor.equals("WHITE")) {
            if (gameToJoin.whiteUsername() != null) {
                return false;
            }
            games.add(new GameData(gameID, username, gameToJoin.blackUsername(), gameToJoin.gameName(), gameToJoin.game()));
            games.remove(gameToJoin);
        }
        else if (desiredColor.equals("BLACK")) {
            if (gameToJoin.blackUsername() != null) {
                return false;
            }
            games.add(new GameData(gameID, gameToJoin.whiteUsername(), username, gameToJoin.gameName(), gameToJoin.game()));
            games.remove(gameToJoin);
        }
        return true;
    }
}
