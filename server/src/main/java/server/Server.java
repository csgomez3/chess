package server;

import io.javalin.*;
import io.javalin.http.Context;
import com.google.gson.*;

public class Server {

    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"))

        // Register your endpoints and exception handlers here.
                .post("/user", this::createUser)
                .get("/user", this::getUser)
                .delete("/user", this::clearUsers)
                .post("/game", this::createGame)
                .get("/game", this::getGame)
                .get("/game", this::listGames)
                .delete("/game", this::clearGames)
                .post("/auth", this::createAuth)
                .get("/auth", this::getAuth)
                .delete("/auth", this::deleteAuth)
                .delete("/auth", this::clearAuthTokens);
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }

    private void createUser(Context ctx) {

    }

    private void getUser(Context ctx) {

    }

    private void clearUsers(Context ctx) {

    }

    private void createGame(Context ctx) {

    }

    private void getGame(Context ctx) {

    }

    private void listGames(Context ctx) {

    }

    private void clearGames(Context ctx) {

    }

    private void createAuth(Context ctx) {

    }

    private void getAuth(Context ctx) {

    }

    private void deleteAuth(Context ctx) {

    }

    private void clearAuthTokens(Context ctx) {

    }
}
