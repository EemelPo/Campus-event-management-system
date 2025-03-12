package org.example.sep_projecta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SettingsController {

    @FXML
    private void handleDeleteAccount() {
        int userId = AuthService.getCurrentUserId();
        if (userId != -1) {
            String query = "DELETE FROM User WHERE userID = ?";
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    AuthService.resetUserId();
                    MainApplication.showLoginScreen();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Delete Account Failed", "Could not delete account.");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Delete Account Failed", "Error");
            }
        }
    }

    @FXML
    private void handleBackHome(){
        try {
            MainApplication.showHomePage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void browsePage(){
        try {
            MainApplication.browsePage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void handleLogout() {
        try {
            MainApplication.showLoginScreen();
            AuthService.resetUserId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}