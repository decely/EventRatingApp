package com.example.eventratingapp.LoginStage;

import com.example.eventratingapp.Data.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class HelloController implements Initializable {
    public TextField login;
    public PasswordField password;
    public static Button loginButton;
    public VBox LoginVbox;
    public VBox UserVbox;
    public HBox AdminVbox;
    public TableView<EventsInfo> eventsView;
    public TableColumn<EventsInfo,String> columnEventName;
    public TableColumn<EventsInfo,String> columnEventPlace;
    public TableColumn<EventsInfo,Date> columnEventDate;
    public TableColumn<EventsInfo,String> columnEventDescription;
    public TableColumn<EventsInfo,Integer> columnEventRating;
    public TextField EventNameField;
    public TextField EventPlaceField;
    public TextArea EventDescriptionField;
    public DatePicker EventDateField;

    DatabaseConnector postgrecon = null;
    private userInfo user;
    private EventsDAO eventsDAO = new EventsDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        var con = postgrecon.getConnection();
        ObservableList<userInfo> userInfos = userDAO.getUserList(con);
        userInfo loginuser = new userInfo(0,"unidentified",password.getText(),login.getText());
        user = LoginCheck.makeCheck(loginuser,userInfos);
        System.out.println(user.getUserStatus());
        if(user.getUserStatus().equals("user")){
            System.out.println("Login as User successful");
            userLogin();
        }
        else if (user.getUserStatus().equals("admin")) {
            System.out.println("Login as Admin successful");
            userLogin();
            AdminVbox.setVisible(true);
        }
        else {
            System.out.println("Login error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserVbox.setVisible(false);
        AdminVbox.setVisible(false);

        databaseInfo database1 = new databaseInfo("jdbc:postgresql://127.0.0.1:5432/JavaTest","postgres","123305");

        try {
            postgrecon = new DatabaseConnector(database1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if (postgrecon.hasConnection()) {
            System.out.println("Waiting for login...");
        }
        else {
            System.out.println("Exiting program...");
            exit(0);
        }
    }

    public void refreshTable() throws SQLException {
        var con = postgrecon.getConnection();
        ObservableList<EventsInfo> EventsInfos = eventsDAO.getEventsList(con);

        LoginVbox.setVisible(false);
        UserVbox.setVisible(true);
        columnEventName.setCellValueFactory(new PropertyValueFactory<EventsInfo,String>("eventName"));
        columnEventDate.setCellValueFactory(new PropertyValueFactory<EventsInfo,Date>("eventDate"));
        columnEventPlace.setCellValueFactory(new PropertyValueFactory<EventsInfo,String>("eventPlace"));
        columnEventRating.setCellValueFactory(new PropertyValueFactory<EventsInfo,Integer>("eventRating"));
        columnEventDescription.setCellValueFactory(new PropertyValueFactory<EventsInfo,String>("eventDescription"));
        eventsView.setItems(EventsInfos);
        System.out.println("Event table load successful");
    }
    public void userLogin() throws SQLException {
        refreshTable();
    }

    public void AddEvent(ActionEvent actionEvent) throws SQLException {
        LocalDate localDate = EventDateField.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date eventDate = Date.from(instant);

        var con = postgrecon.getConnection();
        EventsInfo AddEventInfo = new EventsInfo(EventNameField.getText(),EventPlaceField.getText(),eventDate,EventDescriptionField.getText(),0);

        eventsDAO.addEvent(AddEventInfo, con);
        refreshTable();
    }
}