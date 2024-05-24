package david_seu.snake_game.dao.impl;

import david_seu.snake_game.dao.IUserDao;
import david_seu.snake_game.domain.User;
import jakarta.ejb.Stateless;

import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UserDao extends GenericDao implements IUserDao {
    public UserDao() {
        super();
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

    public void update(User user) {
        try {
            stmt.executeUpdate("UPDATE users SET score = " + user.getScore() + ", time_spent = " + user.getTimeSpent() + " WHERE id = " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User newUser) {
        try {
            stmt.executeUpdate("INSERT INTO users (username, password, score, time_spent) VALUES ('" + newUser.getUsername() + "', '" + newUser.getPassword() + "', " + newUser.getScore() + ", " + newUser.getTimeSpent() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}