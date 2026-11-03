package server;

import io.javalin.*;
import io.javalin.http.Context;
import com.google.gson.*;
import service.UserService;
import service.GameService;
import service.request.*;
import service.result.*;

public class Server {

    private final Javalin javalin;
    private final UserService userService;
    private final GameService gameService;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"))

        // Register your endpoints and exception handlers here.
                .post("/user", this::register)
                .post("/session", this::login)
                .get("/session", this::logout)
                .post("/game", this::createGame)
                .put("/game", this::joinGame)
                .delete("/db", this::clearApp);

        userService = new UserService();
        gameService = new GameService();
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }

    private void register(Context ctx) {

    }

    private void login(Context ctx) {

    }

    private void logout(Context ctx) {

    }

    private void createGame(Context ctx) {

    }

    private void joinGame(Context ctx) {

    }

    private void clearApp(Context ctx) {

    }
}
