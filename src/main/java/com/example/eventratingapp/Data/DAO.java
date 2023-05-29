package com.example.eventratingapp.Data;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAO {
    ObservableList<EventsInfo> getEventsList(Connection con) throws SQLException;
    void addEvent(EventsInfo eventsInfo, Connection con) throws SQLException;
    void updateEvent(int eventid, Connection con) throws SQLException;
    void deleteEvent(int eventid, Connection con);

    ObservableList<RatingInfo> getRatingList(Connection con) throws SQLException;
    void addRating(RatingInfo ratingInfo, Connection con) throws SQLException;
    void updateRating (int ratingid, Connection con);
    void deleteRating (int ratingid, Connection con);

    ObservableList<userInfo> getUserList(Connection con) throws SQLException;
    void addUser(userInfo userinfo, Connection con);
    void updateUser (int userid, Connection con);
    void deleteUser (int userid, Connection con);
}
