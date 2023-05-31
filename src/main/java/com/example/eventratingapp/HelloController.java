package com.example.eventratingapp;

import com.example.eventratingapp.Data.*;
import com.example.eventratingapp.DataBaseStage.DatabaseConnector;
import com.example.eventratingapp.DataBaseStage.databaseInfo;
import com.example.eventratingapp.LoginStage.LoginCheck;
import com.example.eventratingapp.RatingStage.RateCheck;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    public TableColumn columnEventID;
    public Label labelSelectedCell;
    public BorderPane UserToolPane;

    DatabaseConnector postgrecon = null;
    private DAO dbDAO = new DAOImpl();
    private int userID;
    private int eventID;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        var con = postgrecon.getConnection();
        List<userInfo> userInfos = dbDAO.getUserList(con);
        userInfo loginuser = new userInfo(-1,"unidentified",password.getText(),login.getText());
        loginuser = LoginCheck.makeCheck(loginuser,userInfos);
        System.out.println(loginuser.getUserStatus());
        if(loginuser.getUserStatus().equals("user")){
            System.out.println("Login as User successful");
            userLogin();
            UserToolPane.setVisible(true);
        }
        else if (loginuser.getUserStatus().equals("admin")) {
            System.out.println("Login as Admin successful");
            userLogin();
            AdminVbox.setVisible(true);
            UserToolPane.setVisible(false);
        }
        else {
            System.out.println("Login error");
        }
        userID = loginuser.getUserID();
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
        List<EventsInfo> EventsInfos = dbDAO.getEventsList(con);

        columnEventID.setCellValueFactory(new PropertyValueFactory<EventsInfo,Integer>("eventID"));
        columnEventName.setCellValueFactory(new PropertyValueFactory<EventsInfo,String>("eventName"));
        columnEventDate.setCellValueFactory(new PropertyValueFactory<EventsInfo,Date>("eventDate"));
        columnEventPlace.setCellValueFactory(new PropertyValueFactory<EventsInfo,String>("eventPlace"));
        columnEventRating.setCellValueFactory(new PropertyValueFactory<EventsInfo,Integer>("eventRating"));
        columnEventDescription.setCellValueFactory(new PropertyValueFactory<EventsInfo,String>("eventDescription"));
        eventsView.setItems((ObservableList<EventsInfo>) EventsInfos);
        eventsView.getSortOrder().add(columnEventID);
        System.out.println("Event table load successful");
    }
    public void userLogin() throws SQLException {
        LoginVbox.setVisible(false);
        UserVbox.setVisible(true);
        refreshTable();
    }

    public void AddEvent(ActionEvent actionEvent) throws SQLException {
        LocalDate localDate = EventDateField.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date eventDate = Date.from(instant);

        var con = postgrecon.getConnection();
        EventsInfo AddEventInfo = new EventsInfo(0,
                EventNameField.getText(),
                EventPlaceField.getText(),
                eventDate,
                EventDescriptionField.getText(),
                0);

        dbDAO.addEvent(AddEventInfo, con);
        refreshTable();
    }

    public void eventSelected(MouseEvent mouseEvent) {
        if(eventsView.getSelectionModel().getSelectedItem() != null) {
            labelSelectedCell.setText("Выбранное мероприятие: "
                    + columnEventID.getCellData(eventsView.getSelectionModel().getSelectedIndex())
                    + " - "
                    + columnEventName.getCellData(eventsView.getSelectionModel().getSelectedIndex()));
            eventID = (int) columnEventID.getCellData(eventsView.getSelectionModel().getSelectedIndex());
        }
    }

    public void onRatingButtonClicked(ActionEvent actionEvent) throws SQLException {
        var con = postgrecon.getConnection();
        RatingInfo ratingInfo = new RatingInfo(eventID,userID,true);
        List<RatingInfo> ratingData = dbDAO.getRatingList(con);
        if (!RateCheck.isRated(ratingData, eventID, userID)) {
            dbDAO.addRating(ratingInfo, con);
            dbDAO.updateEvent(eventID, con);
            refreshTable();
        }
        else
            System.out.println("This event is already rated");
    }

    public void onChangeUserButtonClicked(ActionEvent actionEvent) {
        LoginVbox.setVisible(true);
        UserVbox.setVisible(false);
        AdminVbox.setVisible(false);
    }
}