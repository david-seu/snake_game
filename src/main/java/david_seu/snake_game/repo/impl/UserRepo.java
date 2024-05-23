package david_seu.snake_game.repo.impl;

import david_seu.snake_game.dao.IUserDao;
import david_seu.snake_game.dao.impl.UserDao;
import david_seu.snake_game.domain.User;
import david_seu.snake_game.repo.IUserRepo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserRepo implements IUserRepo {

    private final IUserDao userDao;

    public UserRepo() {
        userDao = new UserDao();
    }

    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}