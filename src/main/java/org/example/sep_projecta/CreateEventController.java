package org.example.sep_projecta;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEventController {
    @FXML
    DatePicker eventDatePicker;
    @FXML
    TextField eventNameField;
    @FXML
    TextField eventStartField;
    @FXML
    TextField eventEndField;
    @FXML
    TextField eventCategoryField;
    @FXML
    TextField eventLocationField;
    @FXML
    TextField eventDescriptionField;
    @FXML
    Button saveEventButton;


    EventModel event;

    @FXML
    void initialize(){
        saveEventButton.setOnAction(event -> saveEvent());

    }

    private void saveEvent(){
        String eventName = eventNameField.getText();
        String startFieldText = (eventStartField.getText());
        String endFieldText = (eventEndField.getText());
        String eventCategory = eventCategoryField.getText();
        String eventLocation = eventLocationField.getText();
        String eventDescription = eventDescriptionField.getText();
        LocalDate eventDate = eventDatePicker.getValue();
        if (eventName.isEmpty() || startFieldText.isEmpty() || endFieldText.isEmpty() ||
                eventCategory.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty() ||
                eventDate == null) {
            Alert errorMessageAlert = new Alert(Alert.AlertType.ERROR);
            errorMessageAlert.setContentText("Please fill all fields!");
            errorMessageAlert.showAndWait();


            return;
        }

        LocalTime eventStartTime = LocalTime.parse(startFieldText);
        LocalTime eventEndTime = LocalTime.parse(endFieldText);


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
