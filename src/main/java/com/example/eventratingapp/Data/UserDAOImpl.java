package com.example.eventratingapp.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{
    @Override
    public ObservableList<userInfo> getUserList(Connection con) throws SQLException {
        var stmt = con.createStatement();

        ObservableList<userInfo> UserInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingusers;" );
        while ( rs.next() ) {
            UserInfos.add(new userInfo(rs.getInt("userID"),rs.getString("status"),rs.getString("password"),rs.getString("username")));
        }
        stmt.close();
        return UserInfos;
    }
}
