package david_seu.snake_game.service;

import david_seu.snake_game.domain.Game;

public interface IGameService {

    void updateGame(Game game);

    int saveGame(Game game);
}
