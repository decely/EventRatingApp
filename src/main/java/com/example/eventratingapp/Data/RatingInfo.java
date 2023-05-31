package com.example.eventratingapp.Data;

public class RatingInfo {
    private int eventID;
    private int userID;
    private boolean rateStatus;

    public RatingInfo(int eventID, int userID, boolean rateStatus) {
        this.eventID = eventID;
        this.userID = userID;
        this.rateStatus = rateStatus;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(boolean rateStatus) {
        this.rateStatus = rateStatus;
    }
}
