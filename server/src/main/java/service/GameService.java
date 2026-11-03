package service;

import exception.ExistingGameException;
import exception.NoGameFoundException;
import exception.UnauthorizedException;
import service.request.VerifyRequest;
import service.request.CreationRequest;
import service.request.JoinRequest;
import service.result.CreationResult;
import service.result.ColorAssignResult;

import java.util.ArrayList;

import dataaccess.GameDAO;
import dataaccess.AuthDAO;

import model.GameData;

public class GameService {

    GameDAO gameDao;
    AuthDAO authDao;

    public ArrayList<GameData> getGameList(VerifyRequest request) {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        return gameDao.listGames();
    }

    public CreationResult createGame(CreationRequest request) {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("Error 401: unauthorized");
        }
        if (gameDao.getGame(request.gameName()) != null) {
            throw new ExistingGameException("Error 400: bad request");
        }
        GameData newGame = gameDao.createGame(request.gameName());
        return new CreationResult(newGame.gameID());
    }

    public ColorAssignResult joinGame(JoinRequest request) {
        if (authDao.getAuth(request.authToken()) == null) {
            throw new UnauthorizedException("Error 401: unauthorized");
        }
        GameData gameToJoin = gameDao.getGame(request.gameID());
        if (gameToJoin == null) {
            throw new NoGameFoundException("Error 400: bad request: no game with that ID found");
        }
        
    }
}
