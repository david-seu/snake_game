package david_seu.snake_game.service.impl;

import david_seu.snake_game.dao.IGameDao;
import david_seu.snake_game.dao.impl.GameDao;
import david_seu.snake_game.domain.Game;
import david_seu.snake_game.service.IGameService;

public class GameService implements IGameService {

    IGameDao gameDao;

    public GameService() {
        gameDao = new GameDao();
    }

    @Override
    public void updateGame(Game game) {
        gameDao.update(game);
    }

    @Override
    public int saveGame(Game game) {
        return gameDao.save(game);
    }


}
