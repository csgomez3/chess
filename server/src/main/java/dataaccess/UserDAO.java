package dataaccess;

import exception.IncorrectPasswordException;
import model.UserData;
import java.util.ArrayList;
import service.request.RegisterRequest;

public class UserDAO {

    ArrayList<UserData> users = new ArrayList<UserData>();

    public void clearUsers() {
        users.clear();
    }

    public UserData createUser(RegisterRequest request) {
        UserData newUser = new UserData(request.username(), request.password(), request.email());
        users.add(newUser);
        return newUser;
    }

    public UserData getUser(String username) {
        for (UserData user : users) {
            if (user.username().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public UserData getUser(String username, String password) {
        for (UserData user : users) {
            if (user.username().equals(username)) {
                if (user.password().equals(password)) {
                    return user;
                }
                else {
                    throw new IncorrectPasswordException("Error: Unauthorized: incorrect password");
                }
            }
        }
        return null;
    }
}
