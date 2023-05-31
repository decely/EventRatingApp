package com.example.eventratingapp.DataBaseStage;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {
    databaseInfo database1 = new databaseInfo("jdbc:postgresql://127.0.0.1:5432/JavaTest","postgres","");
    DatabaseConnector postgrecon = null;


    @Test
    void testConnection() throws SQLException {
        postgrecon = new DatabaseConnector(database1);
        if (!postgrecon.hasConnection())
        {
            fail("No connection!");
        }
    }
}