package org.example.sep_projecta;

import javafx.application.Platform;
import javafx.scene.control.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateEventControllerTest extends ApplicationTest {

    private CreateEventController controller;

    @BeforeEach
    void setUp() {
        controller = new CreateEventController();
        controller.eventDatePicker = new DatePicker();
        controller.eventNameField = new TextField();
        controller.eventStartField = new TextField();
        controller.eventEndField = new TextField();
        controller.eventCategoryField = new TextField();
        controller.eventLocationField = new TextField();
        controller.eventDescriptionField = new TextField();
        controller.saveEventButton = new Button();
        controller.initialize();
    }

    @Test
    void testSaveEventWithEmptyFields() {
        Platform.runLater(() -> {
            controller.eventNameField.setText("");
            controller.eventStartField.setText("");
            controller.eventEndField.setText("");
            controller.eventCategoryField.setText("");
            controller.eventLocationField.setText("");
            controller.eventDescriptionField.setText("");
            controller.eventDatePicker.setValue(null);

            controller.saveEventButton.fire();

            assertNull(controller.event);
        });
    }

    @Test
    void testSaveEventWithValidFields() {
        Platform.runLater(() -> {
            controller.eventNameField.setText("Event Name");
            controller.eventStartField.setText("10:00");
            controller.eventEndField.setText("12:00");
            controller.eventCategoryField.setText("Category");
            controller.eventLocationField.setText("Location");
            controller.eventDescriptionField.setText("Description");
            controller.eventDatePicker.setValue(LocalDate.now());

            controller.saveEventButton.fire();

            assertNotNull(controller.event);
            assertEquals("Event Name", controller.event.getEventName());
            assertEquals("10:00", controller.event.getEventStartTime());
            assertEquals("12:00", controller.event.getEventEndTime());
            assertEquals("Category", controller.event.getEventCategory());
            assertEquals("Location", controller.event.getEventLocation());
            assertEquals("Description", controller.event.getEventDescription());
            assertEquals(LocalDate.now(), controller.event.getEventDate());
        });
    }
}