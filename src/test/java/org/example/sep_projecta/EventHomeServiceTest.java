package org.example.sep_projecta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventHomeServiceTest {

    private EventHomeService eventHomeService;
    private AuthService authServiceMock;
    private DatabaseConnector databaseConnectorMock;

    @BeforeEach
    void setUp() {
        authServiceMock = mock(AuthService.class);
        databaseConnectorMock = mock(DatabaseConnector.class);
        eventHomeService = new EventHomeService(authServiceMock, databaseConnectorMock);
    }

    @Test
    void testLoadMyEvents() throws SQLException {
        int userId = 1;
        List<EventModel> expectedEvents = Arrays.asList();

        when(authServiceMock.getCurrentUserId()).thenReturn(userId);
        when(databaseConnectorMock.getMyEvents(userId)).thenReturn(expectedEvents);

        List<EventModel> actualEvents = eventHomeService.loadMyEvents();

        assertEquals(expectedEvents, actualEvents);
        verify(authServiceMock).getCurrentUserId();
        verify(databaseConnectorMock).getMyEvents(userId);
    }

    @Test
    void testLoadCreatedEvents() throws SQLException {
        int userId = 1;
        List<EventModel> expectedEvents = Arrays.asList();

        when(authServiceMock.getCurrentUserId()).thenReturn(userId);
        when(databaseConnectorMock.getCreatedEvents(userId)).thenReturn(expectedEvents);

        List<EventModel> actualEvents = eventHomeService.loadCreatedEvents();

        assertEquals(expectedEvents, actualEvents);
        verify(authServiceMock).getCurrentUserId();
        verify(databaseConnectorMock).getCreatedEvents(userId);
    }
}