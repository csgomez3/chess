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
        RegisterRequest request = new Gson().fromJson(ctx.body(), RegisterRequest.class);
        RegisterResult result = userService.register(request);
        ctx.json(result.toString());
    }

    private void login(Context ctx) {
        LoginRequest request = new Gson().fromJson(ctx.body(), LoginRequest.class);
        RegisterResult result = userService.login(request);
        ctx.json(result.toString());
    }

    private void logout(Context ctx) {
        LogoutRequest request = new Gson().fromJson(ctx.body(), LogoutRequest.class);
        userService.logout(request);
        //Web socket makes noise
    }

    private void createGame(Context ctx) {
        CreationRequest request = new Gson().fromJson(ctx.body(), CreationRequest.class);
        CreationResult result = gameService.createGame(request);
        ctx.json(result.toString());
    }

    private void joinGame(Context ctx) {
        JoinRequest request = new Gson().fromJson(ctx.body(), JoinRequest.class);
        ColorAssignResult result = gameService.joinGame(request);
        if (result.result()) {
            ctx.status(200);
        }
    }

    private void clearApp(Context ctx) {
        gameService.clearGames();
        userService.clearUserData();
        ctx.status(200);
    }
}
