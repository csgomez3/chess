package service;

import dataaccess.AuthDAO;

import model.AuthData;

import java.util.ArrayList;

public class AuthService {
    private final AuthDAO authDao = new AuthDAO();

    public String createAuth(String username) {
        AuthData newAuth = authDao.createAuth(username);
        return newAuth.authToken();
    }

    public boolean verifyAuth(String authToken) {
        AuthData toVerify = authDao.getAuth(authToken);
        if (toVerify == null) {
            return false;
        }
        return true;
    }

    public boolean deleteAuth(String authToken) {
        AuthData userToLogout = authDao.getAuth(authToken);
        if (userToLogout == null) {
            return false;
        }
        authDao.deleteAuth(userToLogout);
        return true;
    }

    public ArrayList<AuthData> getAuthList() {
        return authDao.getAuthList();
    }

    public void clearAuthData() {
        authDao.clearAuthTokens();
    }
}
