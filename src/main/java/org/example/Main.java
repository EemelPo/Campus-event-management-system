package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/eventmanagementlogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}