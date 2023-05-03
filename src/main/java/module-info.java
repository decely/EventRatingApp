module com.example.eventratingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.eventratingapp to javafx.fxml;
    exports com.example.eventratingapp;
}