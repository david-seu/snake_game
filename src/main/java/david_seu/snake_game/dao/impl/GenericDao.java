package david_seu.snake_game.dao.impl;

import java.sql.*;

public abstract class GenericDao {

    protected Statement stmt;
    protected Connection conn;

    public GenericDao() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snake_game", "postgres", "Liverpool16");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            System.out.println("eroare la connect:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}