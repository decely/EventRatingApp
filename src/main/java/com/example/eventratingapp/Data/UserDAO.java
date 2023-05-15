package com.example.eventratingapp.Data;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {
    ObservableList<userInfo> getUserList(Connection con) throws SQLException;
}
