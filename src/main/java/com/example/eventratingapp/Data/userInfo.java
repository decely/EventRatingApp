package com.example.eventratingapp.Data;

public class userInfo {
    private int userID;
    private String userStatus;
    private String userPassword;
    private String userLogin;

    public userInfo(int userID, String userStatus, String userPassword, String userLogin) {
        this.userID = userID;
        this.userStatus = userStatus;
        this.userPassword = userPassword;
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
