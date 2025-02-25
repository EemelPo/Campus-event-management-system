import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.sep_projecta.AuthService;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest extends ApplicationTest {

    private LoginController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/login.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testHandleLoginSuccess(FxRobot robot) {
        // Mock the AuthService
        AuthService mockAuthService = mock(AuthService.class);
        when(mockAuthService.auth("user", "pass")).thenReturn(true);
        AuthService.setInstance(mockAuthService); // Assume AuthService uses a singleton pattern

        // Enter username and password
        robot.clickOn("#usernameField").write("user");
        robot.clickOn("#passwordField").write("pass");

        // Click the login button
        robot.clickOn("#loginButton");

        // Check if the correct alert is shown
        robot.interact(() -> {
            Alert alert = (Alert) robot.lookup(".alert").queryAs(Alert.class);
            assertEquals(Alert.AlertType.INFORMATION, alert.getAlertType(), "The alert type should be INFORMATION");
            assertEquals("Login Successful", alert.getTitle(), "The alert title should be 'Login Successful'");
            assertEquals("Welcome, user!", alert.getContentText(), "The alert message should be 'Welcome, user!'");
        });
    }

    @Test
    public void testHandleLoginFailure(FxRobot robot) {
        // Mock the AuthService
        AuthService mockAuthService = mock(AuthService.class);
        when(mockAuthService.auth("user", "wrongpass")).thenReturn(false);
        AuthService.setInstance(mockAuthService); // Assume AuthService uses a singleton pattern

        // Enter username and password
        robot.clickOn("#usernameField").write("user");
        robot.clickOn("#passwordField").write("wrongpass");

        // Click the login button
        robot.clickOn("#loginButton");

        // Check if the correct alert is shown
        robot.interact(() -> {
            Alert alert = (Alert) robot.lookup(".alert").queryAs(Alert.class);
            assertEquals(Alert.AlertType.ERROR, alert.getAlertType(), "The alert type should be ERROR");
            assertEquals("Login Failed", alert.getTitle(), "The alert title should be 'Login Failed'");
            assertEquals("Invalid credentials.", alert.getContentText(), "The alert message should be 'Invalid credentials.'");
        });
    }
}
