package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (AuthService.auth(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials.");
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
