package com.example.eventratingapp.Data;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<EventsInfo> getEventsList(Connection con) throws SQLException;
    void addEvent(EventsInfo eventsInfo, Connection con) throws SQLException;
    void updateEvent(int eventid, Connection con) throws SQLException;
    void deleteEvent(int eventid, Connection con);

    List<RatingInfo> getRatingList(Connection con) throws SQLException;
    void addRating(RatingInfo ratingInfo, Connection con) throws SQLException;
    void updateRating (int ratingid, Connection con);
    void deleteRating (int ratingid, Connection con);

    List<userInfo> getUserList(Connection con) throws SQLException;
    void addUser(userInfo userinfo, Connection con);
    void updateUser (int userid, Connection con);
    void deleteUser (int userid, Connection con);
}
