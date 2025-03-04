package org.example.sep_projecta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private DatabaseConnector mockDatabaseConnector;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        List<EventModel> mockEvents = Arrays.asList(
                new EventModel("Concert", LocalTime.of(19, 0), LocalTime.of(22, 0),
                        "New York", "Music", "Live music concert", LocalDate.of(2025, 3, 10),
                        100, 50, 1),

                new EventModel("Tech Talk", LocalTime.of(14, 0), LocalTime.of(17, 0),
                        "San Francisco", "Technology", "Tech innovations", LocalDate.of(2025, 3, 15),
                        200, 150, 2)
        );

        mockStatic(DatabaseConnector.class);
        when(DatabaseConnector.getAllEvents()).thenReturn(mockEvents);

        eventController = new EventController();
    }

    @Test
    void testFilterEvents_ShouldFilterBySearchText() {
        List<EventModel> filteredEvents = eventController.filterEvents("Concert", null);
        assertEquals(1, filteredEvents.size());
        assertEquals("Concert", filteredEvents.get(0).eventName);
    }

    @Test
    void testFilterEvents_ShouldFilterByDate() {
        List<EventModel> filteredEvents = eventController.filterEvents("", LocalDate.of(2025, 3, 10));
        assertEquals(1, filteredEvents.size());
        assertEquals("Concert", filteredEvents.get(0).eventName);
    }

    @Test
    void testFilterEvents_ShouldFilterByCategory() {
        CheckMenuItem categoryItem = new CheckMenuItem("Music");
        categoryItem.setSelected(true);
        eventController.getCategoryMenuItems().add(categoryItem);

        List<EventModel> filteredEvents = eventController.filterEvents("", null);
        assertEquals(1, filteredEvents.size());
        assertEquals("Music", filteredEvents.get(0).eventCategory);
    }

    @Test
    void testFilterEvents_ShouldFilterByLocation() {
        CheckMenuItem locationItem = new CheckMenuItem("New York");
        locationItem.setSelected(true);
        eventController.getLocationMenuItems().add(locationItem);

        List<EventModel> filteredEvents = eventController.filterEvents("", null);
        assertEquals(1, filteredEvents.size());
        assertEquals("New York", filteredEvents.get(0).eventLocation);
    }

    @Test
    void testPopulateMenuItems_ShouldAddItems() throws SQLException {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        List<String> mockCategories = Arrays.asList("Music", "Technology");

        when(DatabaseConnector.getDistinctValues("category")).thenReturn(mockCategories);

        eventController.populateMenuItems(menuItems, "category");

        assertEquals(2, menuItems.size());
        assertEquals("Music", menuItems.get(0).getText());
        assertEquals("Technology", menuItems.get(1).getText());
    }

    @Test
    void testHandleLogout_ShouldResetUserId() {
        AuthService.auth("testuser", "password123");
        eventController.handleLogout();

        assertEquals(-1, AuthService.getCurrentUserId());
    }
}
