package org.example.sep_projecta;

import com.gluonhq.charm.glisten.control.CardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class EventHomeController {

    private Stage stage;
    @FXML
    public CardPane CreatedEventsCardPane;

    @FXML
    private Text myEventsCountLabel;

    @FXML
    public CardPane MyEventsCardPane;


    @FXML
    private void browsePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementbrowsetest.fxml"));
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Event Management Home");
        stage.show();
    }

    @FXML
    private void settingsPage() {
    }

    @FXML
    private void deleteEvent(MouseEvent event) {
        Button deleteButton = (Button) event.getSource();
        VBox vbox = (VBox) deleteButton.getParent();
        CreatedEventsCardPane.getItems().remove(vbox);
    }

    @FXML
    public void initialize() {
        try {
            List<EventModel> myEvents = DatabaseConnector.getMyEvents(AuthService.getCurrentUserId());
            for (EventModel event : myEvents) {
                VBox eventBox = new VBox();
                eventBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");

                Text eventName = new Text(event.getEventName());
                Text eventType = new Text("Type: " + event.getEventCategory());
                Text eventLocation = new Text("Location: " + event.getEventLocation());
                Text eventDate = new Text("Date: " + event.getEventDate().toString());
                Text eventTime = new Text("Time: " + event.getEventStartTime().toString() + "-" + event.getEventEndTime().toString());

                eventBox.getChildren().addAll(eventName, eventType, eventLocation, eventDate, eventTime);
                MyEventsCardPane.getItems().add(eventBox);
                myEventsCountLabel.setText(Integer.toString(myEvents.size()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<EventModel> createdEvents = DatabaseConnector.getCreatedEvents(AuthService.getCurrentUserId());
            for (EventModel event : createdEvents) {
                VBox eventBox = new VBox();
                eventBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");

                Text eventName = new Text(event.getEventName());
                Text eventType = new Text("Type: " + event.getEventCategory());
                Text eventLocation = new Text("Location: " + event.getEventLocation());
                Text eventDate = new Text("Date: " + event.getEventDate().toString());
                Text eventTime = new Text("Time: " + event.getEventStartTime().toString() + "-" + event.getEventEndTime().toString());

                Button deleteButton = new Button("Delete");
                deleteButton.setOnMouseClicked(this::deleteEvent);

                eventBox.getChildren().addAll(eventName, eventType, eventLocation, eventDate, eventTime, deleteButton);
                CreatedEventsCardPane.getItems().add(eventBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLogout(){
        try {
            MainApplication.showLoginScreen();
            AuthService.resetUserId();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateEvent() throws IOException {
        try {
            MainApplication.showCreateEventPage();
        } catch (IOException e){
            e.printStackTrace();
        }
        }

    @FXML
    private void homePage(){
    }


}