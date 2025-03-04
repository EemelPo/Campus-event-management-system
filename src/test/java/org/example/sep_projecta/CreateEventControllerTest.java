package org.example.sep_projecta;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEventControllerTest extends ApplicationTest {

    @InjectMocks
    private CreateEventController controller;

    @Mock
    private DatabaseConnector databaseConnector;

    @BeforeEach
    void setUp() {
        controller.eventDatePicker = new DatePicker();
        controller.eventNameField = new TextField();
        controller.eventStartField = new TextField();
        controller.eventEndField = new TextField();
        controller.eventCategoryField = new TextField();
        controller.eventLocationField = new TextField();
        controller.eventMaxAttField = new TextField();
        controller.eventAttQuantField = new TextField();
        controller.eventDescriptionField = new TextField();
        controller.saveEventButton = new Button();
    }

    @Test
    void errorWhenFieldsEmpty() throws SQLException {
        controller.eventNameField.setText("");
        controller.eventStartField.setText("");
        controller.eventEndField.setText("");
        controller.eventCategoryField.setText("");
        controller.eventLocationField.setText("");
        controller.eventDescriptionField.setText("");
        controller.eventMaxAttField.setText("0");
        controller.eventAttQuantField.setText("0");
        controller.eventDatePicker.setValue(null);

        controller.saveEvent();

        verifyNoInteractions(databaseConnector);
    }

    @Test
    void errorWhenAttMoreThanMax() throws SQLException {
        controller.eventNameField.setText("Test Event");
        controller.eventStartField.setText("12:00");
        controller.eventEndField.setText("14:00");
        controller.eventCategoryField.setText("Test Category");
        controller.eventLocationField.setText("Test Location");
        controller.eventDescriptionField.setText("Test Description");
        controller.eventMaxAttField.setText("5");
        controller.eventAttQuantField.setText("10");
        controller.eventDatePicker.setValue(LocalDate.now());

        controller.saveEvent();

        verifyNoInteractions(databaseConnector);
    }

    @Test
    void saveEvent() throws SQLException {
        controller.eventNameField.setText("Test Event");
        controller.eventStartField.setText("12:00");
        controller.eventEndField.setText("14:00");
        controller.eventCategoryField.setText("Test Category");
        controller.eventLocationField.setText("Test Location");
        controller.eventDescriptionField.setText("Test Description");
        controller.eventMaxAttField.setText("5");
        controller.eventAttQuantField.setText("3");
        controller.eventDatePicker.setValue(LocalDate.now());

        controller.saveEvent();

    }
}

