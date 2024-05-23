package david_seu.snake_game.controller;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.gson.Gson;
import david_seu.snake_game.domain.User;
import david_seu.snake_game.service.IUserService;
import david_seu.snake_game.service.impl.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;

import javax.naming.InitialContext;
import javax.naming.NamingException;


@RequestScoped
public class UserController extends HttpServlet {

    IUserService userService;

    public UserController() {
        super();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd;

        User user = userService.authenticateUser(username, password);

        if (user != null) {
            rd = request.getRequestDispatcher("/snake.jsp");
            HttpSession session = request.getSession();
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            session.setAttribute("user", userJson);
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }

}