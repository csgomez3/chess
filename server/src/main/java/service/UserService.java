package service;

import service.request.*;
import service.result.*;

import dataaccess.UserDAO;
import dataaccess.AuthDAO;

import model.UserData;
import model.AuthData;

import exception.*;

public class UserService {

    private final UserDAO userDao = new UserDAO();
    private final AuthDAO authDao = new AuthDAO();

    public RegisterResult register(RegisterRequest request) throws AlreadyTakenException {
        if (userDao.getUser(request.username(), null) != null) {
            throw new AlreadyTakenException("Error: username already taken");
        }
        UserData newUser = userDao.createUser(request);
        AuthData newAuth = authDao.createAuth(newUser.username());
        return new RegisterResult(newUser.username(), newAuth.authToken());
    }

    public RegisterResult login(LoginRequest request) throws NoUserFoundException, IncorrectPasswordException {
        UserData returningUser = userDao.getUser(request.username(), request.password());
        if (returningUser == null) {
            throw new NoUserFoundException("Error: bad request: no user with that username found");
        }
        if (!returningUser.password().equals(request.password())) {
            throw new IncorrectPasswordException("Error: unauthorized: incorrect password");
        }
        AuthData newAuth = authDao.createAuth(returningUser.username());
        return new RegisterResult(returningUser.username(), newAuth.authToken());
    }

    public void logout(LogoutRequest request) throws UnauthorizedException {
        AuthData loggingOut = authDao.getAuth(request.authToken());
        if (loggingOut == null) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        authDao.deleteAuth(loggingOut);
    }

    public void clearUserData() {
        authDao.clearAuthTokens();
        userDao.clearUsers();
    }
}
