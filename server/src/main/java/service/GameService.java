package service;

import exception.*;

import service.request.*;
import service.result.*;

import java.util.ArrayList;

import dataaccess.GameDAO;

import model.GameData;

public class GameService {

    private final GameDAO gameDao;
    private final AuthService authService;

    public GameService(AuthService aService) {
        gameDao = new GameDAO();
        authService = aService;
    }

    public ArrayList<GameData> getGameList(String authToken) throws UnauthorizedException {
        if (!authService.verifyAuth(authToken)) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        return gameDao.listGames();
    }

    public CreationResult createGame(CreationRequest request) throws UnauthorizedException, ExistingGameException, BadRequestException {
        if (!authService.verifyAuth(request.authToken())) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        if (gameDao.getGame(request.gameName()) != null) {
            throw new BadRequestException("Error: bad request");
        }
        GameData newGame = gameDao.createGame(request.gameName());
        return new CreationResult(newGame.gameID());
    }

    public void joinGame(JoinRequest request) throws UnauthorizedException, NoGameFoundException, ColorTakenException, BadRequestException {
        if (request.authToken() == null || request.gameID() < 1 || request.gameID() > 9999
                || request.username() == null || request.color() == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (!authService.verifyAuth(request.authToken())) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        if (gameDao.getGame(request.gameID()) == null) {
            throw new NoGameFoundException("Error: bad request: no game with that ID found");
        }
        boolean result = gameDao.updateGame(request.gameID(), request.username(), request.color());
        if (!result) {
            throw new ColorTakenException("Error: color already taken");
        }
    }

    public void clearGames() {
        gameDao.clearGames();
    }

    public ArrayList<GameData> getListOfGames() {
        return gameDao.listGames();
    }
}
