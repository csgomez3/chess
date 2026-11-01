package dataaccess;

import java.util.ArrayList;
import java.util.UUID;

import model.AuthData;

public class AuthDAO {

    ArrayList<AuthData> authList = new ArrayList<AuthData>();

    public void clearAuthTokens() {

    }

    public AuthData createAuth(String username) {
        AuthData newAuth = new AuthData(UUID.randomUUID().toString(), username);
        authList.add(newAuth);
        return newAuth;
    }

    public String getAuth() {
        return new String();
    }

    public boolean deleteAuth() {
        return false;
    }
}
