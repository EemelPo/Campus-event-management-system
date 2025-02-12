package org.example.sep_projecta;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class CreateEventController {
    @FXML
    private DatePicker eventDatePicker;
    @FXML
    private TextField eventNameField;
    @FXML
    private TextField eventStartField;
    @FXML
    private TextField eventEndField;
    @FXML
    private TextField eventCategoryField;
    @FXML
    private TextField eventLocationField;
    @FXML
    private TextField eventDescriptionField;
    @FXML
    private Button saveEventButton;


    private EventModel event;

    @FXML

    private void initialize(){
        saveEventButton.setOnAction(event -> saveEvent());

    }

    private void saveEvent(){
        String eventName = eventNameField.getText();
        String eventStartTime = eventStartField.getText();
        String eventEndTime = eventEndField.getText();
        String eventCategory = eventCategoryField.getText();
        String eventLocation = eventLocationField.getText();
        String eventDescription = eventDescriptionField.getText();
        LocalDate eventDate = eventDatePicker.getValue();
        if (eventName.isEmpty() || eventStartTime.isEmpty() || eventEndTime.isEmpty() ||
                eventCategory.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty() ||
                eventDate == null) {
            Alert errorMessageAlert = new Alert(Alert.AlertType.ERROR);
            errorMessageAlert.setContentText("Please fill all fields!");
            errorMessageAlert.showAndWait();


            return;
        }


        event = new EventModel(eventName, eventStartTime, eventEndTime, eventCategory, eventLocation, eventDescription, eventDate);

        eventNameField.clear();
        eventStartField.clear();
        eventEndField.clear();
        eventCategoryField.clear();
        eventLocationField.clear();
        eventDescriptionField.clear();
        eventDatePicker.setValue(null);

    }


}
