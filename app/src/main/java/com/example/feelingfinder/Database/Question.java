package com.example.feelingfinder.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String question;

    public int answer;

    public int quizId;

    public Question(){
        this.question = "Default Q";
        this.answer = 0;
        this.quizId = -1;
    }
    public Question(String q, int value){
        this.question = q;
        this.answer = value;
        this.quizId = -1;
    }
}
