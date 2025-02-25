package org.example.sep_projecta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventControllerTest {

    @Test
    void testEventControllerConstructor() {
        EventController eventController = new EventController();

        // Verify eventList is initialized with the correct sample events
        List<EventModel> eventList = eventController.getEventList();
        assertEquals(3, eventList.size());

        EventModel event1 = eventList.get(0);
        assertEquals("AI future", event1.getName());
        assertEquals("Category 1", event1.getCategory());
        assertEquals("Location 1", event1.getLocation());
        assertEquals(LocalDate.now(), event1.getDate());
        assertEquals(LocalTime.of(10, 0), event1.getStartTime());
        assertEquals(LocalTime.of(12, 0), event1.getEndTime());

        Event event2 = eventList.get(1);
        assertEquals("OOP is cool", event2.getName());
        assertEquals("Category 2", event2.getCategory());
        assertEquals("Location 2", event2.getLocation());
        assertEquals(LocalDate.now().plusDays(1), event2.getDate());
        assertEquals(LocalTime.of(14, 0), event2.getStartTime());
        assertEquals(LocalTime.of(16, 0), event2.getEndTime());

        Event event3 = eventList.get(2);
        assertEquals("Matrix math optimizations", event3.getName());
        assertEquals("Category 1", event3.getCategory());
        assertEquals("Location 2", event3.getLocation());
        assertEquals(LocalDate.now().plusDays(30), event3.getDate());
        assertEquals(LocalTime.of(16, 0), event3.getStartTime());
        assertEquals(LocalTime.of(18, 0), event3.getEndTime());

        // Verify categoryMenuItems and locationMenuItems are initialized as empty ObservableLists
        ObservableList<MenuItem> categoryMenuItems = eventController.getCategoryMenuItems();
        ObservableList<MenuItem> locationMenuItems = eventController.getLocationMenuItems();
        assertEquals(FXCollections.observableArrayList(), categoryMenuItems);
        assertEquals(FXCollections.observableArrayList(), locationMenuItems);
    }
}