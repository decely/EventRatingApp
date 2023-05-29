package com.example.eventratingapp.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpl implements DAO {
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
    public void updateEvent(int eventid, Connection con) throws SQLException {
        var stmt = con.createStatement();

        String FillQuery = "UPDATE eventratingevents\n"
                + "SET eventrating = eventrating + 1\n"
                + "WHERE eventid = " + eventid + ";";
        stmt.execute(FillQuery);
        System.out.println("EventID "+eventid+ " increased successfully");

        stmt.close();
    }

    @Override
    public void deleteEvent(int eventid, Connection con) {

    }

    @Override
    public ObservableList<RatingInfo> getRatingList(Connection con) throws SQLException {
        var stmt = con.createStatement();

        ObservableList<RatingInfo> ratingInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingrate;" );
        while ( rs.next() ) {
            ratingInfos.add(new RatingInfo(
                    rs.getInt("eventID"),
                    rs.getInt("userID"),
                    rs.getBoolean("ratestatus")));
        }
        stmt.close();
        return ratingInfos;
    }

    @Override
    public void addRating(RatingInfo ratingInfo, Connection con) throws SQLException {
        var stmt = con.createStatement();

        String FillQuery = "INSERT INTO eventratingrate VALUES ('"
                +ratingInfo.getEventID()+"', '"
                +ratingInfo.getUserID()+"',"
                +ratingInfo.isRateStatus()+")";
        stmt.execute(FillQuery);
        System.out.println("EventRating added to eventID "+ratingInfo.getEventID()+ " successfully");

        stmt.close();
    }


    @Override
    public void updateRating(int ratingid, Connection con) {

    }

    @Override
    public void deleteRating(int ratingid, Connection con) {

    }

    @Override
    public ObservableList<userInfo> getUserList(Connection con) throws SQLException {
        var stmt = con.createStatement();

        ObservableList<userInfo> UserInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingusers;" );
        while ( rs.next() ) {
            UserInfos.add(new userInfo(rs.getInt("userID"),
                    rs.getString("status"),
                    rs.getString("password"),
                    rs.getString("username")));
        }
        stmt.close();
        return UserInfos;
    }

    @Override
    public void addUser(userInfo userinfo, Connection con) {

    }

    @Override
    public void updateUser(int userid, Connection con) {

    }

    @Override
    public void deleteUser(int userid, Connection con) {

    }
}
