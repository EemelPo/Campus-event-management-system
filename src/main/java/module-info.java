module org.example.sep_projecta {
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires java.sql;
    requires bcrypt;


    opens org.example.sep_projecta to javafx.fxml;
    exports org.example.sep_projecta;
}