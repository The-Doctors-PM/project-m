package com.example.feelingfinder.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Goal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GoalsDAO goalsDAO();
}
