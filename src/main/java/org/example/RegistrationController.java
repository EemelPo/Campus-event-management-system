package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.io.IOException;

public class RegistrationController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button registerButton;
    @FXML private Button backToLoginButton;

    @FXML
    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (AuthService.usernameExists(username)) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username already exists.");
            return;
        }

        if (AuthService.register(firstName, lastName, username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "User registered successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Could not register user.");
        }
    }

    @FXML
    private void handleBackToLogin() {
        try {
            Main.showLoginScreen();
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