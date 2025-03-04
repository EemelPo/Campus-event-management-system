package org.example.sep_projecta;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private TextField usernameField;
    private TextField passwordField;
    private Button loginButton;
    private Button registerButton;
    private AuthService authServiceMock;
    private MainApplication mainAppMock;

    @BeforeAll
    public static void initJfx() throws InterruptedException {
        // Initialize JavaFX Platform before any test runs
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    public void setUp() {
        // Create spy and mock objects
        usernameField = spy(new TextField());
        passwordField = spy(new TextField());
        loginButton = mock(Button.class);
        registerButton = mock(Button.class);
        authServiceMock = mock(AuthService.class);
        mainAppMock = mock(MainApplication.class);

        // Create instance of LoginController with dependency injection
        // Adjust depending on your constructor (or use setters if required)
        loginController = new LoginController();

        // Assign mocks to LoginController fields
        // Note: if you have refactored to use dependency injection for AuthService and MainApplication,
        // set them accordingly. Otherwise, these static methods must be wrapped.
        // For demonstration, we assume they are non-static (or that you have wrapped them appropriately).
        // If not, you might need to adjust this test.
        loginController.usernameField = usernameField;
        loginController.passwordField = passwordField;
        loginController.loginButton = loginButton;
        loginController.registerButton = registerButton;
    }

    @Test
    public void testHandleLoginSuccess() throws IOException {
        // Use doReturn to stub TextField spies
        doReturn("testUser").when(usernameField).getText();
        doReturn("testPassword").when(passwordField).getText();

        // Stub AuthService's behavior
        when(authServiceMock.auth("testUser", "testPassword")).thenReturn(true);
        when(authServiceMock.getCurrentUserId()).thenReturn(1);

        // Call the handleLogin method
        loginController.handleLogin();

        // Verify that the authentication method and navigation were invoked
        verify(authServiceMock).auth("testUser", "testPassword");
        verify(mainAppMock).showHomePage();
    }

    @Test
    public void testHandleLoginFailure() {
        doReturn("testUser").when(usernameField).getText();
        doReturn("wrongPassword").when(passwordField).getText();

        // Stub failed authentication
        when(authServiceMock.auth("testUser", "wrongPassword")).thenReturn(false);

        // Call the handleLogin method
        loginController.handleLogin();

        // Verify that authentication was invoked with wrong credentials
        verify(authServiceMock).auth("testUser", "wrongPassword");
    }

    @Test
    public void testHandleRegister() throws IOException {
        // Call the handleRegister method
        loginController.handleRegister();

        // Verify that the registration screen is shown via MainApplication
        verify(mainAppMock).showRegistrationScreen();
    }
}