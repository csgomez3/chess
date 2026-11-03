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

    public void updateGame(int gameID, String username, String team) {
        GameData gameToJoin = getGame(gameID);
        if (gameToJoin == null) {
            throw new NoGameFoundException("Error 400: bad request: no game with that ID found");
        }
        String desiredColor = team.toUpperCase();
        if (desiredColor.equals("WHITE")) {
            if (gameToJoin.whiteUsername() != null) {
                throw new ColorTakenException("Error 403: white already taken");
            }
            games.add(new GameData(gameID, username, gameToJoin.blackUsername(), gameToJoin.gameName(), gameToJoin.game()));
            games.remove(gameToJoin);
        }
        else if (desiredColor.equals("BLACK")) {
            if (gameToJoin.blackUsername() != null) {
                throw new ColorTakenException("Error 403: black already taken");
            }
            games.add(new GameData(gameID, gameToJoin.whiteUsername(), username, gameToJoin.gameName(), gameToJoin.game()));
            games.remove(gameToJoin);
        }
    }
}
