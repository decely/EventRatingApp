package com.example.eventratingapp.Data;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class RatingDAOImpl implements RatingDAO{
    @Override
    public ObservableList<RatingInfo> getRatingList(Connection con) throws SQLException {
        return null;
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
}
