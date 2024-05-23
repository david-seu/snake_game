package david_seu.snake_game.dao;

import david_seu.snake_game.domain.User;

public interface IUserDao {

    User findByUsername(String username);
}
