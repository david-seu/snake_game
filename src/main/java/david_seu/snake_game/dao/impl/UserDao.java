package david_seu.snake_game.dao.impl;

import david_seu.snake_game.dao.IUserDao;
import david_seu.snake_game.domain.User;
import jakarta.ejb.Stateless;

import java.sql.PreparedStatement;
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
            String sql = "UPDATE users SET score = ?, time_spent = ? WHERE id = ?";
            System.out.println("Updating user with id: " + user.getId());
            System.out.println("New score: " + user.getScore());
            System.out.println("New time spent: " + user.getTimeSpent());
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getScore());
            pstmt.setInt(2, user.getTimeSpent());
            pstmt.setInt(3, user.getId());

            pstmt.executeUpdate();
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