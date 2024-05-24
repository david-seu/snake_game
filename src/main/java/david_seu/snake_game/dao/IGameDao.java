package david_seu.snake_game.dao;

import david_seu.snake_game.domain.Game;

public interface IGameDao {

    void update(Game game);

    int save(Game game);
}
