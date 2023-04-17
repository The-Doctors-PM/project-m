package com.example.feelingfinder.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Goal.class, Note.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GoalsDAO goalsDAO();

    public abstract NotesDAO notesDAO();
}
