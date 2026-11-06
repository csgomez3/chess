package service;

import exception.*;

import service.request.*;
import service.result.*;

import java.util.ArrayList;

import dataaccess.GameDAO;
import dataaccess.AuthDAO;

import model.GameData;

public class GameService {

    GameDAO gameDao = new GameDAO();
    AuthDAO authDao = new AuthDAO();

    public ArrayList<GameData> getGameList(VerifyRequest request) throws UnauthorizedException {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("unauthorized");
        }
        return gameDao.listGames();
    }

    public CreationResult createGame(CreationRequest request) throws UnauthorizedException, ExistingGameException {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("unauthorized");
        }
        if (gameDao.getGame(request.gameName()) != null) {
            throw new ExistingGameException("bad request");
        }
        GameData newGame = gameDao.createGame(request.gameName());
        return new CreationResult(newGame.gameID());
    }

    public ColorAssignResult joinGame(JoinRequest request) throws UnauthorizedException, NoGameFoundException, ColorTakenException {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("unauthorized");
        }
        if (gameDao.getGame(request.gameID()) == null) {
            throw new NoGameFoundException("Bad Request: no game with that ID found");
        }
        boolean result = gameDao.updateGame(request.gameID(), request.username(), request.color());
        return new ColorAssignResult(result);
    }

    public void clearGames() {
        gameDao.clearGames();
    }
}
