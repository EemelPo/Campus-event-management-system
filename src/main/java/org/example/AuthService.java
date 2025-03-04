package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private static int currentUserId = -1;

    public static boolean auth(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/eventmanagement";
        String dbUser = "testuser";
        String dbPassword = "test";

        String query = "SELECT userID, password FROM User WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    currentUserId = rs.getInt("userID");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean usernameExists(String username) {
        String url = "jdbc:mysql://localhost:3306/eventmanagement";
        String dbUser = "testuser";
        String dbPassword = "test";

        String query = "SELECT COUNT(*) FROM User WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean register(String firstName, String lastName, String username, String password) {
        String url = "jdbc:mysql://localhost:3306/eventmanagement";
        String dbUser = "testuser";
        String dbPassword = "test";

        String query = "INSERT INTO User (firstName, lastName, username, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }
}