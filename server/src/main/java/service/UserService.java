package service;

import service.request.RegisterRequest;
import service.request.LoginRequest;
import service.request.LogoutRequest;
import service.result.RegisterResult;
import service.result.LoginResult;

import dataaccess.UserDAO;
import dataaccess.AuthDAO;

public class UserService {
    public RegisterResult register(RegisterRequest request) {
        if (UserDAO.getUser(request.username() != null)) {
            throw
        }
        UserDAO.createUser();
    }

    public LoginResult login(LoginRequest request) {

    }

    public void logout(LogoutRequest request) {

    }
}
