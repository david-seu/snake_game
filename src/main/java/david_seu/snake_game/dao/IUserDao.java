package david_seu.snake_game.dao;

import david_seu.snake_game.domain.User;

public interface IUserDao {

    User findByUsername(String username);

    void update(User user);

    void save(User newUser);
}
