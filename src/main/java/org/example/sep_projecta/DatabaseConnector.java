package org.example.sep_projecta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/eventmanagement";

    private static final String USER = "root";
    private static final String PASSWORD = "aitog";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static void HandleSaveEvent(EventModel event) throws SQLException {
        String query = "INSERT INTO events (name, startTime, endTime, category, location, description, date, maxAtt, AttQuant) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, event.getEventName());
            statement.setTime(2, java.sql.Time.valueOf(event.getEventStartTime()));
            statement.setTime(3, java.sql.Time.valueOf(event.getEventEndTime()));
            statement.setString(4, event.getEventCategory());
            statement.setString(5, event.getEventLocation());
            statement.setString(6, event.getEventDescription());
            statement.setDate(7, java.sql.Date.valueOf(event.getEventDate()));
            statement.setInt(8,event.getEventMaxAtt());
            statement.setInt(9,event.getEventAttQuant());

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
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

    public static List<EventModel> getMyEvents(int userId) throws SQLException {
        String query = "SELECT e.* FROM event e JOIN attendance a ON e.eventid = a.eventid WHERE a.userid = ?";
        List<EventModel> events = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int eventId = resultSet.getInt("eventid");
                String name = resultSet.getString("name");
                Time startTime = resultSet.getTime("startTime");
                Time endTime = resultSet.getTime("endTime");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");

                EventModel event = new EventModel(eventId, name, startTime.toLocalTime(), endTime.toLocalTime(), category, location, description, date.toLocalDate());
                events.add(event);
            }
        }

        return events;
    }

    public static List<EventModel> getCreatedEvents(int userId) throws SQLException {
        String query = "SELECT e.* FROM event e JOIN teachercreates a ON e.eventid = a.eventid WHERE a.userid = ?";
        List<EventModel> events = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int eventId = resultSet.getInt("eventid");
                String name = resultSet.getString("name");
                Time startTime = resultSet.getTime("startTime");
                Time endTime = resultSet.getTime("endTime");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");

                EventModel event = new EventModel(eventId, name, startTime.toLocalTime(), endTime.toLocalTime(), category, location, description, date.toLocalDate());
                events.add(event);
            }
        }

        return events;

    }
}

