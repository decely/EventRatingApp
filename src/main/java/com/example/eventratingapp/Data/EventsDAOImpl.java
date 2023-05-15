package com.example.eventratingapp.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventsDAOImpl implements EventsDAO{
    @Override
    public ObservableList<EventsInfo> getEventsList(Connection con) throws SQLException {
        var stmt = con.createStatement();

        ObservableList<EventsInfo> EventsInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventrating;" );
        while ( rs.next() ) {
            EventsInfos.add(new EventsInfo(rs.getString("eventname"),rs.getString("eventplace"),rs.getDate("eventdate"),rs.getString("eventdescription"),rs.getInt("eventrating")));
        }
        stmt.close();
        return EventsInfos;
    }
}
