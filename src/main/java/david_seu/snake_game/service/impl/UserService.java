package david_seu.snake_game.service.impl;

import david_seu.snake_game.dao.IUserDao;
import david_seu.snake_game.dao.impl.UserDao;
import david_seu.snake_game.domain.User;
import david_seu.snake_game.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService implements IUserService {

    IUserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }
    @Override
    public User authenticateUser(String username, String password) {
        return  userDao.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void saveUser(User newUser) {
        userDao.save(newUser);
    }
}
