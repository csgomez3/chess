package dataaccess;

import java.util.ArrayList;
import java.util.UUID;

import exception.UnauthorizedException;

import model.AuthData;

public class AuthDAO {

    ArrayList<AuthData> authList = new ArrayList<AuthData>();

    public void clearAuthTokens() {
        for (AuthData auth : authList) {
            deleteAuth(auth);
        }
    }

    public AuthData createAuth(String username) {
        AuthData newAuth = new AuthData(UUID.randomUUID().toString(), username);
        authList.add(newAuth);
        return newAuth;
    }

    public AuthData getAuth(String username) {
        for (AuthData auth : authList) {
            if (auth.username().equals(username)) {
                return auth;
            }
        }
        throw new UnauthorizedException("Error: unauthorized");
    }

    public void deleteAuth(AuthData auth) {

    }
}
