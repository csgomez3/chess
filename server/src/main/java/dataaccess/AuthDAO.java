package dataaccess;

import java.util.ArrayList;
import java.util.UUID;

import model.AuthData;

public class AuthDAO {

    ArrayList<AuthData> authList = new ArrayList<>();

    public void clearAuthTokens() {
        authList.clear();
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

    public ArrayList<AuthData> getAuthList() {
        return this.authList;
    }
}
