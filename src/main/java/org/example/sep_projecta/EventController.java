package org.example.sep_projecta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

    private List<EventModel> eventList;
    private ObservableList<MenuItem> categoryMenuItems;
    private ObservableList<MenuItem> locationMenuItems;
    private Stage stage;

    public EventController() {
        try {
            eventList = DatabaseConnector.getAllEvents();
        } catch (SQLException e) {
            e.printStackTrace();
            eventList = FXCollections.observableArrayList();
        }
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

        // Populate category and location menus from the database
        populateMenuItems(categoryMenuItems, "category");
        populateMenuItems(locationMenuItems, "location");


        // Bind the Menu items to the ObservableLists
        Bindings.bindContent(categoryMenu.getItems(), categoryMenuItems);
        Bindings.bindContent(locationMenu.getItems(), locationMenuItems);

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

        // Add event cards to the FlowPane
        for (EventModel event : eventList) {
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

    private void populateMenuItems(ObservableList<MenuItem> menuItems, String column) {
        try {
            List<String> values = DatabaseConnector.getDistinctValues(column);
            for (String value : values) {
                menuItems.add(new CheckMenuItem(value));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private StackPane createEventCard(EventModel event) {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: lightgray; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        stackPane.setPrefSize(150, 100);

        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-alignment: center-left;");

        Text nameText = new Text(event.getEventName());
        Text categoryText = new Text("Category: " + event.getEventCategory());
        Text locationText = new Text("Location: " + event.getEventLocation());
        Text dateText = new Text("Date: " + event.getEventDate().toString());

        HBox timeBox = new HBox(5);
        timeBox.setStyle("-fx-alignment: center;");

        StackPane timePane = new StackPane();
        timePane.setStyle("-fx-background-color: darkgray; -fx-padding: 5; -fx-border-radius: 10; -fx-background-radius: 10;");
        Text timeText = new Text(event.getEventStartTime().toString() + " - " + event.getEventEndTime().toString());
        timeText.setStyle("-fx-fill: white;");

        timePane.getChildren().add(timeText);
        timeBox.getChildren().add(timePane);

        vBox.getChildren().addAll(nameText, categoryText, locationText, dateText, timeBox);
        stackPane.getChildren().add(vBox);

        System.out.print("Created card for event: " + event.getEventName());

        return stackPane;
    }

    private List<EventModel> filterEvents(String searchText, LocalDate selectedDate) {
        List<String> selectedCategories = categoryMenu.getItems().stream()
                .filter(item -> item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());

        List<String> selectedLocations = locationMenu.getItems().stream()
                .filter(item -> item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());

        return eventList.stream()
                .filter(EventModel -> (searchText.isEmpty() || EventModel.getEventName().toLowerCase().contains(searchText) ||
                        EventModel.getEventCategory().toLowerCase().contains(searchText) ||
                        EventModel.getEventLocation().toLowerCase().contains(searchText)) &&
                        (selectedDate == null || EventModel.getEventDate().equals(selectedDate)) &&
                        (selectedCategories.isEmpty() || selectedCategories.contains(EventModel.getEventCategory())) &&
                        (selectedLocations.isEmpty() || selectedLocations.contains(EventModel.getEventLocation())))
                .collect(Collectors.toList());

    }

    @FXML
    public void searchEvents() {
        String searchText = searchField.getText().toLowerCase();
        LocalDate selectedDate = datePicker.getValue();
        List<EventModel> filteredEvents = filterEvents(searchText, selectedDate);
        eventFlowPane.getChildren().clear();
        for (EventModel event : filteredEvents) {
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

    public List<EventModel> getEventList() {
        return eventList;
    }

    public ObservableList<MenuItem> getCategoryMenuItems() {
        return categoryMenuItems;
    }

    public ObservableList<MenuItem> getLocationMenuItems() {
        return locationMenuItems;
    }
}