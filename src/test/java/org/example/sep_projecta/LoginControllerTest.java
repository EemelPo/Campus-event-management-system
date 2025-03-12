package org.example.sep_projecta;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class LoginControllerTest {

    private LoginController loginController;
    private TextField usernameField;
    private TextField passwordField;

    @BeforeEach
    public void setUp() {
        loginController = new LoginController();
        // Instead of mocking TextField, create actual instances
        usernameField = new TextField();
        passwordField = new TextField();
        loginController.usernameField = usernameField;
        loginController.passwordField = passwordField;
    }

    @Test
    public void testHandleLoginSuccess() throws Exception {
        // Arrange: Set up valid credentials on the actual text fields.
        String testUsername = "testUser";
        String testPassword = "testPass";
        usernameField.setText(testUsername);
        passwordField.setText(testPassword);

        // Use Mockito's static mocking feature with try-with-resources.
        try (MockedStatic<AuthService> authServiceMock = Mockito.mockStatic(AuthService.class);
             MockedStatic<MainApplication> mainAppMock = Mockito.mockStatic(MainApplication.class)) {

            // Stub static methods.
            authServiceMock.when(() -> AuthService.auth(testUsername, testPassword)).thenReturn(true);
            authServiceMock.when(AuthService::getCurrentUserId).thenReturn(1);

            // Act: Handle the login.
            loginController.handleLogin();

            // Assert: Verify that MainApplication.showHomePage() was invoked.
            mainAppMock.verify(MainApplication::showHomePage, Mockito.times(1));
        }
    }

    @Test
    public void testHandleLoginFailure() throws Exception {
        // Arrange: Set up invalid credentials.
        String testUsername = "badUser";
        String testPassword = "badPass";
        usernameField.setText(testUsername);
        passwordField.setText(testPassword);

        try (MockedStatic<AuthService> authServiceMock = Mockito.mockStatic(AuthService.class);
             MockedStatic<MainApplication> mainAppMock = Mockito.mockStatic(MainApplication.class)) {

            // Stub the static method to simulate failed authentication.
            authServiceMock.when(() -> AuthService.auth(testUsername, testPassword)).thenReturn(false);

            // Act: Handle the login.
            loginController.handleLogin();

            // Assert: Verify that MainApplication.showHomePage() is never called.
            mainAppMock.verify(MainApplication::showHomePage, never());
        }
    }

    @Test
    public void testHandleRegister() throws Exception {
        // Arrange: Use static mocking for MainApplication.
        try (MockedStatic<MainApplication> mainAppMock = Mockito.mockStatic(MainApplication.class)) {
            // Act: Trigger registration handler.
            loginController.handleRegister();

            // Assert: Verify that MainApplication.showRegistrationScreen() was invoked once.
            mainAppMock.verify(MainApplication::showRegistrationScreen, Mockito.times(1));
        }
    }
}