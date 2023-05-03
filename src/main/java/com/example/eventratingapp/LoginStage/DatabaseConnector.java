package com.example.eventratingapp.LoginStage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
        private Connection connection;

        public DatabaseConnector(databaseInfo connection) throws SQLException {
            this.connection = DriverManager.getConnection(connection.getDB_URL(), connection.getUSER(), connection.getPASS());
        }

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        //Подключение к базе данных
        public boolean hasConnection() {

            System.out.println("Testing connection to PostgreSQL JDBC");

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
                e.printStackTrace();
            }

            System.out.println("PostgreSQL JDBC Driver successfully connected");

            if (connection != null) {
                System.out.println("You successfully connected to database now");
                return true;
            } else {
                System.out.println("Failed to make connection to database");
                return false;
            }
        }
        public void closeConnection() throws SQLException {
            connection.close();
        }
}
