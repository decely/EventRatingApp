package com.example.eventratingapp.Data;

import java.util.Date;

public class EventsInfo {
    private int eventID;
    private String eventName;
    private String eventPlace;
    private Date eventDate;
    private String eventDescription;
    private int eventRating;

    public EventsInfo(int eventID, String eventName, String eventPlace, Date eventDate, String eventDescription, int eventRating) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventRating = eventRating;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getEventRating() {
        return eventRating;
    }

    public void setEventRating(int eventRating) {
        this.eventRating = eventRating;
    }
}
