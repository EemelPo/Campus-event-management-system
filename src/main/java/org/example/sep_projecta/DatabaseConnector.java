package org.example.sep_projecta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveEvent(EventModel event) throws SQLException {
        String query = "INSERT INTO events (name, startTime, endTime, category, location, description, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, event.getEventName());
            statement.setTime(2, java.sql.Time.valueOf(event.getEventStartTime()));
            statement.setTime(3, java.sql.Time.valueOf(event.getEventEndTime()));
            statement.setString(4, event.getEventCategory());
            statement.setString(5, event.getEventLocation());
            statement.setString(6, event.getEventDescription());
            statement.setDate(7, java.sql.Date.valueOf(event.getEventDate()));

            statement.executeUpdate();
        }
    }
}

