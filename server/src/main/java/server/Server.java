package server;

import io.javalin.*;
import io.javalin.http.Context;
import com.google.gson.*;
import service.AuthService;
import service.UserService;
import service.GameService;
import service.request.*;
import service.result.*;
import dataaccess.DataAccessException;
import exception.*;

public class Server {

    private final Javalin javalin;
    private final AuthService authService;
    private final UserService userService;
    private final GameService gameService;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"))

        // Register your endpoints and exception handlers here.
                .post("/user", this::register)
                .post("/session", this::login)
                .delete("/session", this::logout)
                .post("/game", this::createGame)
                .get("/game", this::listGames)
                .put("/game", this::joinGame)
                .delete("/db", this::clearApp);

        authService = new AuthService();
        userService = new UserService(authService);
        gameService = new GameService(authService);
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
        try {
            RegisterResult result = userService.register(request);
            ctx.status(200);
            ctx.json(new Gson().toJson(result));
        }
        catch (BadRequestException e) {
            ctx.status(400);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
        catch (AlreadyTakenException e) {
            ctx.status(403);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
    }

    private void login(Context ctx) {
        LoginRequest request = new Gson().fromJson(ctx.body(), LoginRequest.class);
        try {
            RegisterResult result = userService.login(request);
            ctx.status(200);
            ctx.json(new Gson().toJson(result));
        }
        catch (BadRequestException e) {
            ctx.status(400);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
        catch (NoUserFoundException | IncorrectPasswordException e) {
            ctx.status(401);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
    }

    private void logout(Context ctx) {
        String authToken = new Gson().fromJson(ctx.body(), String.class);
        try {
            userService.logout(authToken);
            ctx.status(200);
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
    }

    private void listGames(Context ctx) {
        String authToken = new Gson().fromJson(ctx.body(), String.class);
        try {
            ctx.json(new Gson().toJson(gameService.getGameList(authToken)));
            ctx.status(200);
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
    }

    private void createGame(Context ctx) {
        CreationRequest request = new Gson().fromJson(ctx.body(), CreationRequest.class);
        try {
            CreationResult result = gameService.createGame(request);
            ctx.json(result.toString());
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
        catch (BadRequestException | ExistingGameException e) {
            ctx.status(400);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
    }

    private void joinGame(Context ctx) {
        JoinRequest request = new Gson().fromJson(ctx.body(), JoinRequest.class);
        try {
            gameService.joinGame(request);
            ctx.status(200);
        }
        catch (UnauthorizedException e) {
            ctx.status(401);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
        catch (NoGameFoundException | BadRequestException e) {
            ctx.status(400);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
        catch (ColorTakenException e) {
            ctx.status(403);
            ErrorFormat error = new ErrorFormat(e.getMessage());
            ctx.json(new Gson().toJson(error));
        }
    }

    private void clearApp(Context ctx) {
        gameService.clearGames();
        userService.clearUserData();
        authService.clearAuthData();
        ctx.status(200);
    }
}
