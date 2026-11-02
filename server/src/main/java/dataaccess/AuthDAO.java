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

    public AuthData getAuth(String authToken) {
        for (AuthData auth : authList) {
            if (auth.authToken().equals(authToken)) {
                return auth;
            }
        }
        return null;
    }

    public void deleteAuth(AuthData auth) {
        for (AuthData authEntry: authList) {
            if (authEntry.equals(auth)) {
                authList.remove(auth);
                return;
            }
        }
    }
}
