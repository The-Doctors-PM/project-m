package com.example.feelingfinder;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class FeelingFinder extends Application {
    private static Context context;
    private static final String CHANNEL_ID = "FF00";
    private static final String CHANNEL_NAME = "Feeling Finder Notifications";
    private static final String CHANNEL_DESCRIPTION = "Notifications channel per the application " +
            "\"Feeling Finder\"";

    public void onCreate() {
        super.onCreate();
        FeelingFinder.context = getApplicationContext();
    }

    public static String getChannelId() {
        return CHANNEL_ID;
    }
    public static String getChannelName(){ return CHANNEL_NAME;}
    public static String getChannelDescription() {
        return CHANNEL_DESCRIPTION;
    }

    public static Context getAppContext() {
        return FeelingFinder.context;
    }
}
