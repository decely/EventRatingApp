package com.example.eventratingapp.LoginStage;

import com.example.eventratingapp.EventsInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseReader {
    public static String LoginCheck (String loginText, String passwordText, Connection connection) throws SQLException {
        var stmt = connection.createStatement();
        String login;
        String password;
        boolean admin;

        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingusers;" );
        while ( rs.next() ) {
            login = rs.getString("username");
            password = rs.getString("password");
            if(loginText.equals(login) && passwordText.equals(password)){
                admin = rs.getBoolean("admin");
                if(admin)
                {
                    return "Admin";
                }
                else
                    return "User";
            }
        }
        stmt.close();
        return "Udentified";
    }

    public static ObservableList<EventsInfo> eventsRead(Connection connection) throws SQLException {
        var stmt = connection.createStatement();

        ObservableList<EventsInfo> EventsInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventrating;" );
        while ( rs.next() ) {
            EventsInfos.add(new EventsInfo(rs.getString("eventname"),rs.getString("eventplace"),rs.getDate("eventdate"),rs.getString("eventdescription"),rs.getInt("eventrating")));
        }
        stmt.close();
        return EventsInfos;
    }
}
