package com.example.eventratingapp.Data;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public interface RatingDAO {
    ObservableList<RatingInfo> getRatingList(Connection con) throws SQLException;
    void addRating(RatingInfo ratingInfo, Connection con) throws SQLException;
}
