package service;

import exception.*;

import service.request.*;
import service.result.*;

import java.util.ArrayList;

import dataaccess.GameDAO;

public class GameService {

    private final GameDAO gameDao;
    private final AuthService authService;

    public GameService(AuthService aService) {
        gameDao = new GameDAO();
        authService = aService;
    }

    public ListWrapper getGameList(String authToken) throws UnauthorizedException, BadRequestException {
        if (authToken == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (!authService.verifyAuth(authToken)) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        return new ListWrapper(gameDao.listGames());
    }

    public CreationResult createGame(String authToken, CreationRequest request)
            throws UnauthorizedException, ExistingGameException, BadRequestException {
        if (authToken == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (!authService.verifyAuth(authToken)) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        if (request.gameName() == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (gameDao.getGame(request.gameName()) != null) {
            throw new ExistingGameException("Error: bad request");
        }
        GameFormat newGame = gameDao.createGame(request.gameName());
        return new CreationResult(newGame.gameID());
    }

    public void joinGame(String authToken, JoinRequest request)
            throws BadRequestException, UnauthorizedException, NoGameFoundException, ColorTakenException {
        if (authToken == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (!authService.verifyAuth(authToken)) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        String username = authService.getUsername(authToken);
        if (request.gameID() < 1 || request.gameID() > 9999) {
            throw new BadRequestException("Error: bad request");
        }
        if (gameDao.getGame(request.gameID()) == null) {
            throw new NoGameFoundException("Error: bad request: no game with that ID found");
        }
        if (request.playerColor() == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (!request.playerColor().equals("WHITE") && !request.playerColor().equals("BLACK")) {
            throw new BadRequestException("Error: bad request");
        }
        boolean result = gameDao.updateGame(request.gameID(), username, request.playerColor());
        if (!result) {
            throw new ColorTakenException("Error: color already taken");
        }
    }

    public void clearGames() {
        gameDao.clearGames();
    }

    public ArrayList<GameFormat> getListOfGames() {
        return gameDao.listGames();
    }
}
