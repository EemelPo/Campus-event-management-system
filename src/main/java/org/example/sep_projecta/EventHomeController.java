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


public class EventHomeController {

    private Stage stage;
    @FXML
    public CardPane CreatedEventsCardPane;

    @FXML
    private Text myEventsCountLabel;


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

        String x = myEventsCountLabel.getText();
        int y = Integer.parseInt(x);
        int newCount = y - 1;
        myEventsCountLabel.setText(Integer.toString(newCount));
    }

    @FXML
    private void homePage(){
    }


}