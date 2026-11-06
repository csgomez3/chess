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

    public ColorAssignResult joinGame(JoinRequest request) throws UnauthorizedException {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("unauthorized");
        }
        gameDao.updateGame(request.gameID(), request.username(), request.color());
        return new ColorAssignResult(true);
    }

    public void clearGames() {
        gameDao.clearGames();
    }
}
