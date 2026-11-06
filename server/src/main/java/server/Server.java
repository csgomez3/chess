package server;

import io.javalin.*;
import io.javalin.http.Context;
import com.google.gson.*;
import service.UserService;
import service.GameService;
import service.request.*;
import service.result.*;
import dataaccess.DataAccessException;
import exception.*;

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

    private void register(Context ctx) throws DataAccessException {
        RegisterRequest request = new Gson().fromJson(ctx.body(), RegisterRequest.class);
        try {
            RegisterResult result = userService.register(request);
            ctx.status(200);
            ctx.json(new Gson().toJson(result));
        }
        catch (AlreadyTakenException e) {
            ctx.status(403);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
    }

    private void login(Context ctx) throws DataAccessException {
        LoginRequest request = new Gson().fromJson(ctx.body(), LoginRequest.class);
        try {
            RegisterResult result = userService.login(request);
            ctx.status(200);
            ctx.json(new Gson().toJson(result));
        }
        catch (NoUserFoundException e) {
            ctx.status(400);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
        catch (IncorrectPasswordException e) {
            ctx.status(401);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
    }

    private void logout(Context ctx) throws DataAccessException {
        LogoutRequest request = new Gson().fromJson(ctx.body(), LogoutRequest.class);
        try {
            userService.logout(request);
            ctx.status(200);
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
    }

    private void listGames(Context ctx) throws DataAccessException {
        VerifyRequest request = new Gson().fromJson(ctx.body(), VerifyRequest.class);
        try {
            ctx.json(new Gson().toJson(gameService.getGameList(request)));
            ctx.status(200);
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
    }

    private void createGame(Context ctx) throws DataAccessException {
        CreationRequest request = new Gson().fromJson(ctx.body(), CreationRequest.class);
        try {
            CreationResult result = gameService.createGame(request);
            ctx.json(result.toString());
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
        catch (ExistingGameException e) {
            ctx.status(400);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
    }

    private void joinGame(Context ctx) throws DataAccessException {
        JoinRequest request = new Gson().fromJson(ctx.body(), JoinRequest.class);
        try {
            ColorAssignResult result = gameService.joinGame(request);
            if (result.result()) {
                ctx.status(200);
            }
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
        catch (NoGameFoundException e) {
            ctx.status(400);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
        catch (ColorTakenException e) {
            ctx.status(403);
            ctx.json(new Gson().toJson(e.getMessage()));
        }
    }

    private void clearApp(Context ctx) throws DataAccessException {
        gameService.clearGames();
        userService.clearUserData();
        ctx.status(200);
    }
}
