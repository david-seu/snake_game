package david_seu.snake_game.dao.impl;

import david_seu.snake_game.dao.IGameDao;
import david_seu.snake_game.domain.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao extends GenericDao implements IGameDao {
    @Override
    public void update(Game game) {
        try {
            String sql = "UPDATE games SET SCORE = ?, snake_positions = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, game.getScore());
            pstmt.setString(2, game.getSnakePositions());
            pstmt.setInt(3, game.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int save(Game game) {
        try {
            String sql = "INSERT INTO games (score, user_id, snake_positions) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, game.getScore());
            pstmt.setInt(2, game.getUserId());
            pstmt.setString(3, game.getSnakePositions());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
