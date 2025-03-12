package org.example.sep_projecta;

import java.sql.SQLException;
import java.util.List;

public class EventHomeService {

    private final AuthService authService;
    private final DatabaseConnector databaseConnector;

    public EventHomeService(AuthService authService, DatabaseConnector databaseConnector) {
        this.authService = authService;
        this.databaseConnector = databaseConnector;
    }

    // Retrieves events where the user participates.
    public List<EventModel> loadMyEvents() throws SQLException {
        int userId = authService.getCurrentUserId();
        return databaseConnector.getMyEvents(userId);
    }

    // Retrieves events created by the user.
    public List<EventModel> loadCreatedEvents() throws SQLException {
        int userId = authService.getCurrentUserId();
        return databaseConnector.getCreatedEvents(userId);
    }
}