package com.example.eventratingapp.RatingStage;

import com.example.eventratingapp.Data.RatingInfo;
import javafx.collections.ObservableList;

public class RateCheck {
    public static boolean isRated(ObservableList<RatingInfo> ratingData, int eventID, int userID) {
        final boolean[] found = {false};
        ratingData.forEach(ratingInfo -> {
            if(eventID == ratingInfo.getEventID() && userID == ratingInfo.getUserID() && ratingInfo.isRateStatus())
            {
                found[0] = true;
            }
        });
        return found[0];
    }
}
