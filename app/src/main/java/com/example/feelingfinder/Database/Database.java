package com.example.feelingfinder.Database;

import android.content.Context;

import androidx.room.Room;

import com.example.feelingfinder.Utility.FeelingFinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {
    private static Context context = FeelingFinder.getAppContext();

    private static AppDatabase db;
    private Database(){}

    public static void buildAppDatabase(){
        //TODO: Allow main thread queries should be a temporary solution!!
        Database.db = Room.databaseBuilder(FeelingFinder.getAppContext(),
                AppDatabase.class, "FeelingFinderDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getAppDatabase() {
        return Database.db;
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
        System.out.println("Notes in the database deleted");
    }

    public static void importMockData(){
        boolean nextStatus = false;
        for (int i = 0; i < 10; i++){
            int date = DateToStringConverter.dateToInt(LocalDate.now()) - i*2;
            System.out.println("Today: " + DateToStringConverter.dateToInt(LocalDate.now()) + "\nNew: " + date);
            Note n = new Note(date,"Mock Note #" + i);
            db.notesDAO().addNote(n);
            Random rand = new Random();
            Goal g = new Goal("Do " + rand.nextInt(100) + " things");
            g.status = nextStatus;
            nextStatus = !nextStatus;
            db.goalsDAO().addGoal(g);
        }
    }

    public static void wipeAllData(){
        Database.wipeGoals();
        Database.wipeNotes();
    }


}
