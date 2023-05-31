package com.example.eventratingapp.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Этот класс - имплементация интерфейса дао, он служит для манипуляцией с базой данных PostreSQL
 *
 * @author Фролкин
 *
 */

public class DAOImpl implements DAO {

    /**
     * Этот метод получает список мероприятий с помощью запроса в базе данных
     * @param con Информация, необходимая для подключения к базе данных
     * @return Список мероприятий
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public ObservableList<EventsInfo> getEventsList(Connection con) throws SQLException {
        var stmt = con.createStatement();

        ObservableList<EventsInfo> EventsInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingevents;" );
        while ( rs.next() ) {
            EventsInfos.add(new EventsInfo(rs.getInt("eventID"),
                    rs.getString("eventname"),
                    rs.getString("eventplace"),
                    rs.getDate("eventdate"),
                    rs.getString("eventdescription"),
                    rs.getInt("eventrating")));
        }
        stmt.close();
        return EventsInfos;
    }

    /**
     * Этод метод добавляет новое мероприятие в базу данных с помощью запроса
     * @param eventsInfo Информация о мероприятии, которое будет добавлено в базу данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void addEvent(EventsInfo eventsInfo, Connection con) throws SQLException {
        var stmt = con.createStatement();

        String FillQuery = "INSERT INTO eventratingevents VALUES ('"
                +eventsInfo.getEventName()+"', '"
                +eventsInfo.getEventPlace()+"', '"
                +eventsInfo.getEventDate()+"','"
                +eventsInfo.getEventDescription()+"','"
                +eventsInfo.getEventRating()+"',default)";
        stmt.execute(FillQuery);
        System.out.println("Event "+eventsInfo.getEventName()+ " added successfully");

        stmt.close();
    }

    /**
     * Этот метод изменяет существующее мероприятие в базе данных с помощью запроса
     * @param eventid ID мероприятия, которое будет изменено
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void updateEvent(int eventid, Connection con) throws SQLException {
        var stmt = con.createStatement();

        String FillQuery = "UPDATE eventratingevents\n"
                + "SET eventrating = eventrating + 1\n"
                + "WHERE eventid = " + eventid + ";";
        stmt.execute(FillQuery);
        System.out.println("EventID "+eventid+ " increased successfully");

        stmt.close();
    }

    /**
     * Этот метод удаляет существующее мероприятие в базе данных с помощью запроса
     * @param eventid ID мероприятия, которое будет удалено
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void deleteEvent(int eventid, Connection con) throws SQLException {
        var stmt = con.createStatement();
    }

    /**
     * Этот метод получает список отметок о посещаемости мероприятий с помощью запроса
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     * @return Список отметок о посещаемости
     */
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

    /**
     * Этот метод добавляет отметку о посещении мероприятия в базу данных с помощью запроса
     * @param ratingInfo Информация о отметке посещения, которая будет добавлена в базу данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
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

    /**
     * Этот метод изменяет информацию о отметке о посещении мероприятия в базе данных с помощью запроса
     * @param ratingid ID отметки о посещении, которая будет изменена в базе данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void updateRating(int ratingid, Connection con) throws SQLException {
        var stmt = con.createStatement();
    }

    /**
     * Этот метод удаляет отметку о посещении мероприятия в базе данных с помощью запроса
     * @param ratingid ID отметки о посещении, которая будет удалена в базе данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void deleteRating(int ratingid, Connection con) throws SQLException {
        var stmt = con.createStatement();
    }

    /**
     * Этот метод получает список пользователей в базе данных с помощью запроса
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     * @return Список пользователей
     */
    @Override
    public ObservableList<userInfo> getUserList(Connection con) throws SQLException {
        var stmt = con.createStatement();

        ObservableList<userInfo> UserInfos = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM eventratingusers;" );
        while ( rs.next() ) {
            UserInfos.add(new userInfo(rs.getInt("userID"),
                    rs.getString("status"),
                    rs.getString("password"),
                    rs.getString("username")));
        }
        stmt.close();
        return UserInfos;
    }

    /**
     * Этот метод добавляет пользователя в базу данных с помощью запроса
     * @param userinfo Информация о пользователе, который будет добавлен в базу данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void addUser(userInfo userinfo, Connection con) throws SQLException {
        var stmt = con.createStatement();
    }

    /**
     * Этот метод изменяет существующего пользователя в базе данных с помощью запроса
     * @param userid ID пользователя, который будет изменён в базе данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void updateUser(int userid, Connection con) throws SQLException {
        var stmt = con.createStatement();
    }

    /**
     * Этот метод удаляет существующего пользователя в базе данных с помощью запроса
     * @param userid ID пользователя, который будет удален в базе данных
     * @param con Информация, необходимая для подключения к базе данных
     * @throws SQLException Вызывает ошибку при проблемах работы с PostgreSQL
     */
    @Override
    public void deleteUser(int userid, Connection con) throws SQLException {
        var stmt = con.createStatement();
    }
}
