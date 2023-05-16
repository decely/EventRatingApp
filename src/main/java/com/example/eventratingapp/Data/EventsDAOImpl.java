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
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingevents;" );
        while ( rs.next() ) {
            EventsInfos.add(new EventsInfo(rs.getInt("eventID"),
                    rs.getString("eventname"),
                    rs.getString("eventplace"),
                    rs.getDate("eventdate"),
                    rs.getString("eventdescription"),
                    rs.getInt("eventrating")));
        }
        stmt.close();
        return EventsInfos;
    }

    @Override
    public void addEvent(EventsInfo eventsInfo, Connection con) throws SQLException {
        var stmt = con.createStatement();

        String FillQuery = "INSERT INTO eventratingevents VALUES ('"
                +eventsInfo.getEventName()+"', '"
                +eventsInfo.getEventPlace()+"', '"
                +eventsInfo.getEventDate()+"','"
                +eventsInfo.getEventDescription()+"','"
                +eventsInfo.getEventRating()+"',default)";
        stmt.execute(FillQuery);
        System.out.println("Event "+eventsInfo.getEventName()+ " added successfully");

        stmt.close();
    }

    @Override
    public void increaseRating(int eventid, Connection con) throws SQLException {
        var stmt = con.createStatement();

        String FillQuery = "UPDATE eventratingevents\n"
                + "SET eventrating = eventrating + 1\n"
                + "WHERE eventid = " + eventid + ";";
        stmt.execute(FillQuery);
        System.out.println("EventID "+eventid+ " increased successfully");

        stmt.close();
    }
}
