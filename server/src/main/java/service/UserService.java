package service;

import service.request.RegisterRequest;
import service.request.LoginRequest;
import service.request.LogoutRequest;
import service.result.RegisterResult;
import service.result.LoginResult;

import dataaccess.UserDAO;
import dataaccess.AuthDAO;

import model.UserData;
import model.AuthData;

import exception.AlreadyTakenException;

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

    public LoginResult login(LoginRequest request) {
        return new LoginResult();
    }

    public void logout(LogoutRequest request) {

    }
}
