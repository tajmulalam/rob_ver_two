package com.boombboomrobux.rewardapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by RAFI on 9/18/2016.
 */

public class SharedPreferenceValue {
    public static void setLoggedInUser(Context context, long userID) {
        SharedPreferences settings = context.getSharedPreferences("loggedUser", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("userID", userID);
        editor.commit();
    }

    public static long getLoggedinUser(Context context) {
        SharedPreferences settings = context.getSharedPreferences("loggedUser", 0);
        long userID = settings.getLong("userID", -1);
        return userID;
    }

    public static void clearLoggedInuserData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("loggedUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

}
