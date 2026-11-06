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
            throw new UnauthorizedException("Error: unauthorized");
        }
        return gameDao.listGames();
    }

    public CreationResult createGame(CreationRequest request) throws UnauthorizedException, ExistingGameException {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("Error 401: unauthorized");
        }
        if (gameDao.getGame(request.gameName()) != null) {
            throw new ExistingGameException("Error 400: bad request");
        }
        GameData newGame = gameDao.createGame(request.gameName());
        return new CreationResult(newGame.gameID());
    }

    public ColorAssignResult joinGame(JoinRequest request) throws UnauthorizedException {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("Error 401: unauthorized");
        }
        gameDao.updateGame(request.gameID(), request.username(), request.color());
        return new ColorAssignResult(true);
    }

    public void clearGames() {
        gameDao.clearGames();
    }
}
