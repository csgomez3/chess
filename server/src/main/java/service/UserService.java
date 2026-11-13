package service;

import service.request.*;
import service.result.*;

import dataaccess.UserDAO;

import model.UserData;

import exception.*;

import java.util.ArrayList;

public class UserService {

    private final UserDAO userDao;
    private final AuthService authService;

    public UserService(AuthService aService) {
        userDao = new UserDAO();
        authService = aService;
    }

    public RegisterResult register(RegisterRequest request) throws AlreadyTakenException, BadRequestException {
        if (request.username() == null || request.password() == null || request.email() == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (userDao.getUser(request.username()) != null) {
            throw new AlreadyTakenException("Error: username already taken");
        }
        UserData newUser = userDao.createUser(request);
        String authToken = authService.createAuth(newUser.username());
        return new RegisterResult(newUser.username(), authToken);
    }

    public RegisterResult login(LoginRequest request) throws NoUserFoundException, IncorrectPasswordException, BadRequestException {
        if (request.username() == null || request.password() == null) {
            throw new BadRequestException("Error: bad request");
        }
        UserData returningUser = userDao.getUser(request.username());
        if (returningUser == null) {
            throw new NoUserFoundException("Error: bad request: no user with that username found");
        }
        if (!returningUser.password().equals(request.password())) {
            throw new IncorrectPasswordException("Error: unauthorized: incorrect password");
        }
        String authToken = authService.createAuth(returningUser.username());
        return new RegisterResult(returningUser.username(), authToken);
    }

    public void logout(String authToken) throws UnauthorizedException {
        if (authToken == null) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        boolean result = authService.deleteAuth(authToken);
        if (!result) {
            throw new UnauthorizedException("Error: unauthorized");
        }
    }

    public ArrayList<UserData> getUserList() {
        return userDao.getUserList();
    }

    public void clearUserData() {
        userDao.clearUsers();
    }
}
