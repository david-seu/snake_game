package david_seu.snake_game.repo;

import david_seu.snake_game.domain.User;

public interface IUserRepo {

    User findUserByUsername(String username);

}
