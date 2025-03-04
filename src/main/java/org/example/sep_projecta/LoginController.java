package org.example.sep_projecta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.io.IOException;

public class LoginController {
    @FXML public TextField usernameField;
    @FXML public TextField passwordField;
    @FXML public Button loginButton;
    @FXML public Button registerButton;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (AuthService.auth(username, password)) {
            int userId = AuthService.getCurrentUserId();
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            try{
                MainApplication.showHomePage();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials.");
        }
    }

    @FXML
    public void handleRegister() {
        try {
            MainApplication.showRegistrationScreen();
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