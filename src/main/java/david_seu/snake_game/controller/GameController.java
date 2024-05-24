package david_seu.snake_game.controller;

import com.google.gson.Gson;
import david_seu.snake_game.domain.Game;
import david_seu.snake_game.service.IGameService;
import david_seu.snake_game.service.impl.GameService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GameController", urlPatterns = {"/game"})
public class GameController extends HttpServlet {

    private final IGameService gameService;

    public GameController() {
        super();
        gameService = new GameService();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();

        Gson gson = new Gson();
        Game game = gson.fromJson(payload, Game.class);
        gameService.updateGame(game);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();
        Gson gson = new Gson();
        Game game = gson.fromJson(payload, Game.class);
        int gameId = gameService.saveGame(game);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"id\":" + gameId + "}");
        response.getWriter().flush();
    }
}
