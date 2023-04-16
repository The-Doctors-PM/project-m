package com.example.feelingfinder.Database;

import android.content.Context;

import androidx.room.Room;

import com.example.feelingfinder.FeelingFinder;

import java.util.List;

public class Data {
    private static Context context = FeelingFinder.getAppContext();

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

    public static void wipeGoals(){
        List<Goal> lg = db.goalsDAO().getAll();
        for (Goal g: lg) {
            db.goalsDAO().deleteGoal(g);
        }
        System.out.println("Goals in the database deleted");
    }

    public static void wipeNotes(){
        List<Note> ln = db.notesDAO().getAll();
        for (Note n: ln) {
            db.notesDAO().deleteNote(n);
        }
        System.out.println("Goals in the database deleted");
    }


}
