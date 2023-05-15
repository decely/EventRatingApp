package com.example.eventratingapp.Data;

public class userInfo {
    private int userID;
    private String userStatus;

    public userInfo(int userID, String userStatus) {
        this.userID = userID;
        this.userStatus = userStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
