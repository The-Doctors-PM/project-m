package com.example.feelingfinder.Database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.feelingfinder.FeelingFinder;

public class Data {
    private static Context context = FeelingFinder.getAppContext();
    private static String KEY = "todaysNote";
    private static SharedPreferences instance = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);

    private static AppDatabase db;
    private Data(){}

    public static void buildAppDatabase(){
        // Allow main thread queries should be a temporary solution!!
        Data.db = Room.databaseBuilder(FeelingFinder.getAppContext(),
                AppDatabase.class, "FeelingFinderDB").allowMainThreadQueries().build();
    }

    public static AppDatabase getAppDatabase() {
        return Data.db;
    }

    public static SharedPreferences.Editor getEditor(){
        return instance.edit();
    }

    public static SharedPreferences getInstance(){
        return instance;
    }



}
