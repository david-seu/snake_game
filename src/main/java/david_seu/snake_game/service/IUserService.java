package david_seu.snake_game.service;

import david_seu.snake_game.domain.User;

public interface IUserService {

    User authenticateUser(String username, String password);
}
