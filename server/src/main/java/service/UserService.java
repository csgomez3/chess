package service;

import service.request.RegisterRequest;
import service.request.LoginRequest;
import service.request.LogoutRequest;
import service.result.RegisterResult;
import service.result.LoginResult;

import dataaccess.UserDAO;
import dataaccess.AuthDAO;

import model.UserData;

public class UserService {

    private final UserDAO userDao = new UserDAO();

    public RegisterResult register(RegisterRequest request) {
        if (userDao.getUser(request.username()) != null) {
            //Throw an error
        }
        userDao.createUser(request);
    }

    public LoginResult login(LoginRequest request) {

    }

    public void logout(LogoutRequest request) {

    }
}
