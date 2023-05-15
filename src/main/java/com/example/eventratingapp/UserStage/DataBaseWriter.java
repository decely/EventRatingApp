package com.example.eventratingapp.UserStage;

import com.example.eventratingapp.Data.EventsInfo;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseWriter {

    public static void AddEvent(EventsInfo addEventInfo, Connection connection) throws SQLException {
        var stmt = connection.createStatement();

        String FillQuery = "INSERT INTO eventrating VALUES ('"+addEventInfo.getEventName()+"', '"+addEventInfo.getEventPlace()+"', '"+addEventInfo.getEventDate()+"','"+addEventInfo.getEventDescription()+"','"+addEventInfo.getEventRating()+"')";
        stmt.execute(FillQuery);
        System.out.println("Event "+addEventInfo.getEventName()+ " added successfully");

        stmt.close();
    }
}
