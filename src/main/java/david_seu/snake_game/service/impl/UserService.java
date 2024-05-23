package david_seu.snake_game.service.impl;

import david_seu.snake_game.domain.User;
import david_seu.snake_game.repo.IUserRepo;
import david_seu.snake_game.repo.impl.UserRepo;
import david_seu.snake_game.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService implements IUserService {

    IUserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo();
    }
    @Override
    public User authenticateUser(String username, String password) {
        return  userRepo.findUserByUsername(username);
    }
}
