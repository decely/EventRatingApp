package com.example.eventratingapp.LoginStage;

import com.example.eventratingapp.LoginStage.DataBaseReader;
import com.example.eventratingapp.LoginStage.DatabaseConnector;
import com.example.eventratingapp.LoginStage.databaseInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class HelloController implements Initializable {
    public TextField login;
    public PasswordField password;
    public static Button loginButton;
    DatabaseConnector postgrecon = null;
    Stage stage;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws SQLException {
        var con = postgrecon.getConnection();
        if(DataBaseReader.LoginCheck(login.getText(),password.getText(),con).equals("User")){
            System.out.println("Login as User successful");
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            postgrecon.closeConnection();
        }
        else if (DataBaseReader.LoginCheck(login.getText(),password.getText(),con).equals("Admin")) {
            System.out.println("Login as Admin successful");
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            postgrecon.closeConnection();
        }
        else {
            System.out.println("Login error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        databaseInfo database1 = new databaseInfo("jdbc:postgresql://127.0.0.1:5432/JavaTest","postgres","123305");

        try {
            postgrecon = new DatabaseConnector(database1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        var con = postgrecon.getConnection();

        if (postgrecon.hasConnection()) {
            System.out.println("Waiting for login...");
        }
        else {
            System.out.println("Exiting program...");
            exit(0);
        }

    }
}