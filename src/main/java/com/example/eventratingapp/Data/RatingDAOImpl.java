package com.example.eventratingapp.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingDAOImpl implements RatingDAO{
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

}
