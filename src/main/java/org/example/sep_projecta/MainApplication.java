package org.example.sep_projecta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementlogin.fxml"));
            FXMLLoader fxmlLoader2 = new FXMLLoader(MainApplication.class.getResource("eventmanagementhome.fxml"));
            FXMLLoader fxmlLoader3 = new FXMLLoader(MainApplication.class.getResource("CreateEvent.fxml"));
            FXMLLoader fxmlLoader4 = new FXMLLoader(MainApplication.class.getResource("eventmanagementbrowsetest.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            Scene scene2 = new Scene(fxmlLoader2.load());
            Scene scene3 = new Scene(fxmlLoader3.load());
            Scene scene4 = new Scene(fxmlLoader4.load());
            stage.setScene(scene1);
            stage.setTitle("Event Management Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}