package org.example.sep_projecta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class  AuthService {
    private static int currentUserId = -1;

    public static boolean auth(String username, String password) {
        String query = "SELECT userID, password FROM User WHERE username = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPasswordHash = rs.getString("password");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storedPasswordHash);
                if (result.verified) {
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
        String query = "SELECT COUNT(*) FROM User WHERE username = ?";

        try (Connection conn = DatabaseConnector.getConnection();
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

    public static boolean register(String firstName, String lastName, String email, String phoneNumber, boolean isTeacher, String username, String password) {
        String query = "INSERT INTO User (firstName, lastName, email, phoneNumber, isTeacher, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setBoolean(5, isTeacher);
            stmt.setString(6, username);
            stmt.setString(7, bcryptHashString);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void resetUserId(){
        currentUserId = -1;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }
}