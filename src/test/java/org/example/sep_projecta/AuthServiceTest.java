package org.example.sep_projecta;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private MockedStatic<DatabaseConnector> mockedStatic;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize static mock
        mockedStatic = Mockito.mockStatic(DatabaseConnector.class);
        when(DatabaseConnector.getConnection()).thenReturn(mockConnection);
    }

    @AfterEach
    void tearDown() {
        // Close static mock after each test
        mockedStatic.close();
    }

    @Test
    void testAuth_SuccessfulLogin() throws SQLException {
        String username = "testuser";
        String password = "securepassword";
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.valueOf(true));
        when(mockResultSet.getString("password")).thenReturn(hashedPassword);
        when(mockResultSet.getInt("userID")).thenReturn(Integer.valueOf(1));

        assertTrue(AuthService.auth(username, password));
        assertEquals(1, AuthService.getCurrentUserId());
    }

    @Test
    void testAuth_FailedLogin() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.valueOf(false));

        assertFalse(AuthService.auth("wronguser", "wrongpassword"));
        assertEquals(-1, AuthService.getCurrentUserId());
    }

    @Test
    void testUsernameExists_ReturnsTrue() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.valueOf(true));
        when(mockResultSet.getInt(1)).thenReturn(Integer.valueOf(1));

        assertTrue(AuthService.usernameExists("existinguser"));
    }

    @Test
    void testUsernameExists_ReturnsFalse() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.valueOf(true));
        when(mockResultSet.getInt(1)).thenReturn(Integer.valueOf(0));

        assertFalse(AuthService.usernameExists("newuser"));
    }

    @Test
    void testRegister_Success() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(Integer.valueOf(1));

        boolean result = AuthService.register("John", "Doe", "john@example.com", "1234567890", false, "johndoe", "password123");
        assertTrue(result);
    }

    @Test
    void testRegister_Failure() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(Integer.valueOf(0));

        boolean result = AuthService.register("Jane", "Smith", "jane@example.com", "0987654321", false, "janesmith", "password456");
        assertFalse(result);
    }

    @Test
    void testResetUserId() {
        AuthService.resetUserId();
        assertEquals(-1, AuthService.getCurrentUserId());
    }
}
