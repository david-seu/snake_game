package david_seu.snake_game.dao.impl;

import david_seu.snake_game.dao.IUserDao;
import david_seu.snake_game.domain.User;
import jakarta.ejb.Stateless;

import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UserDao extends GenericDao<User> implements IUserDao {
    public UserDao() {
        super(User.class);
    }

    public User findByUsername(String username) {
        ResultSet rs;
        User u = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("score"), rs.getInt("time_spent"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}