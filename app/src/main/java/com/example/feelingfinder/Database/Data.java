package com.example.feelingfinder.Database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.feelingfinder.FeelingFinder;

import java.util.List;

public class Data {
    private static Context context = FeelingFinder.getAppContext();
    private static String KEY = "todaysNote";
    private static SharedPreferences instance = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);

    private static AppDatabase db;
    private Data(){}

    public static void buildAppDatabase(){
        // Allow main thread queries should be a temporary solution!!
        Data.db = Room.databaseBuilder(FeelingFinder.getAppContext(),
                AppDatabase.class, "FeelingFinderDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
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

    public static void wipeDatabase(){
        List<Goal> lg = db.goalsDAO().getAll();
        for (Goal g: lg) {
            db.goalsDAO().deleteGoal(g);
        }
        System.out.println("Database wiped");
    }


}
