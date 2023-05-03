module com.example.eventratingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.eventratingapp to javafx.fxml;
    exports com.example.eventratingapp;
    exports com.example.eventratingapp.LoginStage;
    opens com.example.eventratingapp.LoginStage to javafx.fxml;
}