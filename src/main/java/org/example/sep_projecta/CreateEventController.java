package org.example.sep_projecta;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
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
    TextField eventMaxAttField;
    @FXML
    TextField eventAttQuantField;
    @FXML
    TextField eventDescriptionField;
    @FXML
    Button saveEventButton;


    EventModel event;

    @FXML
    void initialize(){
        saveEventButton.setOnAction(event -> {
            try {
                saveEvent();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void saveEvent() throws SQLException {
        String eventName = eventNameField.getText();
        String startFieldText = (eventStartField.getText());
        String endFieldText = (eventEndField.getText());
        String eventCategory = eventCategoryField.getText();
        String eventLocation = eventLocationField.getText();
        String eventDescription = eventDescriptionField.getText();
        LocalDate eventDate = eventDatePicker.getValue();
        int eventMax = Integer.parseInt(eventMaxAttField.getText());
        int eventAtt = Integer.parseInt(eventAttQuantField.getText());
        if (eventName.isEmpty() || startFieldText.isEmpty() || endFieldText.isEmpty() ||
                eventCategory.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty() || eventMax == 0|| eventAtt == 0||
                eventDate == null) {
            Alert errorMessageAlert = new Alert(Alert.AlertType.ERROR);
            errorMessageAlert.setContentText("Please fill all fields!");
            errorMessageAlert.showAndWait();


            return;
        }

        LocalTime eventStartTime = LocalTime.parse(startFieldText);
        LocalTime eventEndTime = LocalTime.parse(endFieldText);


        event = new EventModel(eventName, eventStartTime, eventEndTime, eventCategory, eventLocation, eventDescription, eventDate, eventMax, eventAtt);

        DatabaseConnector.HandleSaveEvent(event);

        eventNameField.clear();
        eventStartField.clear();
        eventEndField.clear();
        eventCategoryField.clear();
        eventLocationField.clear();
        eventDescriptionField.clear();
        eventMaxAttField.clear();
        eventAttQuantField.clear();
        eventDatePicker.setValue(null);

    }


}
