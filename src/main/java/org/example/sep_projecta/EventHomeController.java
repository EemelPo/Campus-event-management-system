package org.example.sep_projecta;

import com.gluonhq.charm.glisten.control.CardPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class EventHomeController {

    @FXML
    public CardPane CreatedEventsCardPane;

    @FXML
    private Text myEventsCountLabel;


    @FXML
    private void browsePage() {
        // Handle browse page button press
    }

    @FXML
    private void settingsPage() {
        // Handle settings page button press
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

    public void homePage(MouseEvent mouseEvent) {
    }


}