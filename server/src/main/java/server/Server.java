package server;

import io.javalin.*;
import io.javalin.http.Context;
import com.google.gson.*;
import model.UserData;
import model.GameData;
import model.AuthData;
import service.UserService;
import service.request.RegisterRequest;
import service.result.RegisterResult;

public class Server {

    private final Javalin javalin;
    private final UserService userService;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"))

        // Register your endpoints and exception handlers here.
                .post("/db", this::register)
                .post("/db", this::login)
                .get("/db", this::logout)
                .post("/db", this::createGame)
                .put("/db", this::joinGame)
                .delete("/db", this::clearApp);

        userService = new UserService();
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }

    private void register(Context ctx) {
        UserData user = new Gson().fromJson(ctx.body(), UserData.class);
        RegisterRequest request = new RegisterRequest(user.username(), user.password(), user.email());
        RegisterResult result = userService.register(request);
        ctx.json(result.toString());
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
