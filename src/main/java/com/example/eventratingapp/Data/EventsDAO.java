package com.example.eventratingapp.Data;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;


public interface EventsDAO {
    ObservableList<EventsInfo> getEventsList(Connection con) throws SQLException;
}
