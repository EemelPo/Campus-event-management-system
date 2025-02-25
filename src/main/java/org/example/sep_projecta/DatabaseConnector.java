package org.example.sep_projecta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/eventmanagement";
    private static final String USER = "testuser";
    private static final String PASSWORD = "Test1234!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveEvent(EventModel event) throws SQLException {
        String query = "INSERT INTO Event (name, startTime, endTime, category, location, description, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

    public static List<EventModel> getAllEvents() throws SQLException {
        String query = "SELECT * FROM Event";
        List<EventModel> events = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EventModel event = new EventModel(
                        resultSet.getString("name"),
                        resultSet.getTime("startTime").toLocalTime(),
                        resultSet.getTime("endTime").toLocalTime(),
                        resultSet.getString("category"),
                        resultSet.getString("location"),
                        resultSet.getString("description"),
                        resultSet.getDate("date").toLocalDate()
                );
                events.add(event);
            }
        }
        return events;
    }

    public static List<String> getDistinctValues(String column) throws SQLException {
        String query = "SELECT DISTINCT " + column + " FROM Event";
        List<String> values = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                values.add(resultSet.getString(column));
            }
        }
        return values;
    }
}

