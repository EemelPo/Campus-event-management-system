package org.example.sep_projecta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventController {

    @FXML
    private FlowPane eventFlowPane;
    @FXML
    private TextField searchField;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Menu categoryMenu;
    @FXML
    private Menu locationMenu;
    @FXML
    private DatePicker datePicker;

    private List<Event> eventList;
    private ObservableList<MenuItem> categoryMenuItems;
    private ObservableList<MenuItem> locationMenuItems;
    private Stage stage;

    public EventController() {
        eventList = new ArrayList<>();
        // Add some sample events
        eventList.add(new Event("AI future", "Category 1", "Location 1", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0)));
        eventList.add(new Event("OOP is cool", "Category 2", "Location 2", LocalDate.now().plusDays(1), LocalTime.of(14, 0), LocalTime.of(16, 0)));
        eventList.add(new Event("Matrix math optimizations", "Category 1", "Location 2", LocalDate.now().plusDays(30), LocalTime.of(16, 0), LocalTime.of(18, 0)));

        // Initialize the ObservableLists for menu items
        categoryMenuItems = FXCollections.observableArrayList();
        locationMenuItems = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        System.out.println("initialize method called");

        // Bind the prefWrapLength of the FlowPane to the viewport width of the ScrollPane
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            eventFlowPane.prefWrapLengthProperty().set(newValue.getWidth());
        });

        // Add a listener to update the Menu items when the ObservableList changes
        categoryMenuItems.addListener((javafx.collections.ListChangeListener<MenuItem>) change -> {
            categoryMenu.getItems().setAll(categoryMenuItems);
        });
        locationMenuItems.addListener((javafx.collections.ListChangeListener<MenuItem>) change -> {
            locationMenu.getItems().setAll(locationMenuItems);
        });

        // Add sample items to the ObservableLists
        categoryMenuItems.add(new CheckMenuItem("Category 1"));
        categoryMenuItems.add(new CheckMenuItem("Category 2"));
        locationMenuItems.add(new CheckMenuItem("Location 1"));
        locationMenuItems.add(new CheckMenuItem("Location 2"));

        // Add event cards to the FlowPane
        for (Event event : eventList) {
            eventFlowPane.getChildren().add(createEventCard(event));
        }

        // Add listener to searchField to detect text changes
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchEvents();
        });

        // Add listener to datePicker to detect date changes
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            searchEvents();
        });

        // Add listener to categoryMenu CheckMenuItems
        for (MenuItem item : categoryMenu.getItems()) {
            if (item instanceof CheckMenuItem) {
                ((CheckMenuItem) item).selectedProperty().addListener((observable, oldValue, newValue) -> {
                    searchEvents();
                });
            }
        }

        // Add listener to locationMenu CheckMenuItems
        for (MenuItem item : locationMenu.getItems()) {
            if (item instanceof CheckMenuItem) {
                ((CheckMenuItem) item).selectedProperty().addListener((observable, oldValue, newValue) -> {
                    searchEvents();
                });
            }
        }

    }

    private StackPane createEventCard(Event event) {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: lightgray; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        stackPane.setPrefSize(150, 100);

        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-alignment: center-left;");

        Text nameText = new Text(event.getName());
        Text categoryText = new Text("Category: " + event.getCategory());
        Text locationText = new Text("Location: " + event.getLocation());
        Text dateText = new Text("Date: " + event.getDate().toString());

        HBox timeBox = new HBox(5);
        timeBox.setStyle("-fx-alignment: center;");

        StackPane timePane = new StackPane();
        timePane.setStyle("-fx-background-color: darkgray; -fx-padding: 5; -fx-border-radius: 10; -fx-background-radius: 10;");
        Text timeText = new Text(event.getStartTime().toString() + " - " + event.getEndTime().toString());
        timeText.setStyle("-fx-fill: white;");

        timePane.getChildren().add(timeText);
        timeBox.getChildren().add(timePane);

        vBox.getChildren().addAll(nameText, categoryText, locationText, dateText, timeBox);
        stackPane.getChildren().add(vBox);

        return stackPane;
    }

    private List<Event> filterEvents(String searchText, LocalDate selectedDate) {
        List<String> selectedCategories = categoryMenu.getItems().stream()
                .filter(item -> item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());

        List<String> selectedLocations = locationMenu.getItems().stream()
                .filter(item -> item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());

        return eventList.stream()
                .filter(event -> (searchText.isEmpty() || event.getName().toLowerCase().contains(searchText) ||
                        event.getCategory().toLowerCase().contains(searchText) ||
                        event.getLocation().toLowerCase().contains(searchText)) &&
                        (selectedDate == null || event.getDate().equals(selectedDate)) &&
                        (selectedCategories.isEmpty() || selectedCategories.contains(event.getCategory())) &&
                        (selectedLocations.isEmpty() || selectedLocations.contains(event.getLocation())))
                .collect(Collectors.toList());
    }

    @FXML
    public void searchEvents() {
        String searchText = searchField.getText().toLowerCase();
        LocalDate selectedDate = datePicker.getValue();
        List<Event> filteredEvents = filterEvents(searchText, selectedDate);
        eventFlowPane.getChildren().clear();
        for (Event event : filteredEvents) {
            eventFlowPane.getChildren().add(createEventCard(event));
        }
    }

    @FXML
    private void homePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("eventmanagementhome.fxml"));
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Event Management Home");
        stage.show();
    }
}