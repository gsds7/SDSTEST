
package org.example.sds3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnector {
    // БАЗУ ПОДКЛЮЧ
    private String url = "jdbc:mysql://localhost:3306/users";
    private String username = "root";
    private String password = "12345";


    private Connection connection;

    public void DBConn() {
        try {

            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Подключено к БД");
            }
        } catch (SQLException e) {
            System.err.println("Не подключился к БД");
            e.printStackTrace();
        }
    }


    public boolean TableConn(String login, String password) {
        try {
            if (connection == null) DBConn();
            String sql = "SELECT * FROM user WHERE Login = ? AND Password = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean RegUser(String login, String password, String gender, String name, String lastName) {
        try {
            if (connection == null) DBConn();
            String sql = "INSERT INTO user (Login, Password, gender, Name, lastName) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, gender);
            stmt.setString(4, name);
            stmt.setString(5, lastName);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}