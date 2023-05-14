package com.example.feelingfinder.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String question;
    @NonNull
    public int answer;
    @NonNull
    public int quizId;

    public Question(){
    }
}
