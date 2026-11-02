package service;

import service.request.RegisterRequest;
import service.request.LoginRequest;
import service.request.LogoutRequest;
import service.result.RegisterResult;

import dataaccess.UserDAO;
import dataaccess.AuthDAO;

import model.UserData;
import model.AuthData;

import exception.AlreadyTakenException;
import exception.NoUserFoundException;
import exception.UnauthorizedException;

public class UserService {

    private final UserDAO userDao = new UserDAO();
    private final AuthDAO authDao = new AuthDAO();

    public RegisterResult register(RegisterRequest request) {
        if (userDao.getUser(request.username(), null) != null) {
            throw new AlreadyTakenException("Error 403: username already taken");
        }
        UserData newUser = userDao.createUser(request);
        AuthData newAuth = authDao.createAuth(newUser.username());
        return new RegisterResult(newUser.username(), newAuth.authToken());
    }

    public RegisterResult login(LoginRequest request) {
        UserData returningUser = userDao.getUser(request.username(), request.password());
        if (returningUser == null) {
            throw new NoUserFoundException("Error 400: Bad Request: no user with that username found");
        }
        AuthData newAuth = authDao.createAuth(returningUser.username());
        return new RegisterResult(returningUser.username(), newAuth.authToken());
    }

    public void logout(LogoutRequest request) {
        AuthData loggingOut = authDao.getAuth(request.authToken());
        if (loggingOut == null) {
            throw new UnauthorizedException("Error 401: unauthorized");
        }
        authDao.deleteAuth(loggingOut);
    }
}
