package david_seu.snake_game.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import david_seu.snake_game.domain.User;
import david_seu.snake_game.service.IUserService;
import david_seu.snake_game.service.impl.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RequestScoped
@WebServlet(name = "UserController", urlPatterns = {"/user/login", "/user/register", "/user/update"})
public class UserController extends HttpServlet {

    private final IUserService userService;

    public UserController() {
        super();
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/user/login")) {
            login(request, response);
        } else if (path.equals("/user/register")) {
            register(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create a new User object
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Save the new user in the database
        userService.saveUser(newUser);

        // Forward to the maim page
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd;

        User user = userService.authenticateUser(username, password);

        if (user != null) {
            rd = request.getRequestDispatcher("/play");
            HttpSession session = request.getSession();

            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            session.setAttribute("user", userJson);
        } else {
            rd = request.getRequestDispatcher("/error");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder buffer = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();

        Gson gson = new Gson();
        User user = gson.fromJson(payload, User.class);
        userService.updateUser(user);
        response.setStatus(200);
    }

}