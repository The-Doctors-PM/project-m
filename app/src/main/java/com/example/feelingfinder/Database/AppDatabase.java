package com.example.feelingfinder.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Goal.class, Note.class, Quiz.class}, version = 9)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GoalsDAO goalsDAO();

    public abstract NotesDAO notesDAO();

    public abstract QuizDAO quizDAO();
}
