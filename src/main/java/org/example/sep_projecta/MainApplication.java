package org.example.sep_projecta;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainApplication.primaryStage = primaryStage;
        showLoginScreen();
    }

    public static void showLoginScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementlogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void showSettings() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            primaryStage.setTitle("Settings");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void browsePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementbrowsetest.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Event management home");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showRegistrationScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementregistration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementhome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showCreateEventPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CreateEvent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Create Event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnector.getConnection();
        launch();
    }
}