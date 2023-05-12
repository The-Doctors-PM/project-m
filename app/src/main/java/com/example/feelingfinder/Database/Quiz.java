package com.example.feelingfinder.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "quizzes")
public class Quiz {

    @PrimaryKey @NonNull
    public int id;

    @NonNull
    public int overall;

    @NonNull
    public int anxiety;

    @NonNull
    public int happiness;




    public Quiz(){
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
        this.overall = 0;
        this.anxiety = 0;
        this.happiness = 0;
    }

    public Quiz(int overall, int anxiety, int happiness){
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
        this.overall = overall;
        this.anxiety = anxiety;
        this.happiness = happiness;
    }


}
