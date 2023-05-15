package com.example.eventratingapp.LoginStage;

import com.example.eventratingapp.Data.userInfo;
import javafx.collections.ObservableList;

public class LoginCheck {
    public static userInfo makeCheck(userInfo loginuser, ObservableList<userInfo> userInfos) {
        userInfos.forEach(userInfo -> {
            if(loginuser.getUserLogin().equals(userInfo.getUserLogin()) && loginuser.getUserPassword().equals(userInfo.getUserPassword()))
            {
                loginuser.setUserID(userInfo.getUserID());
                loginuser.setUserLogin(userInfo.getUserLogin());
                loginuser.setUserPassword(userInfo.getUserPassword());
                loginuser.setUserStatus(userInfo.getUserStatus());
            }
        });
        return loginuser;
    }
}
