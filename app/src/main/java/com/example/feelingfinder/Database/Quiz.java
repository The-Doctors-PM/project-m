package com.example.feelingfinder.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "quizzes")
public class Quiz {

    @PrimaryKey @NonNull
    public int id; //aka date
    public int dayRating;
    public boolean hadAnxiety;
    public boolean wasSatisfied;
    public boolean betterTomorrow;


    public Quiz(){
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
        this.dayRating = 0;
        this.hadAnxiety = false;
        this.wasSatisfied = false;
        this.betterTomorrow = false;

    }

    public Quiz(int dayRating, boolean hadAnxiety, boolean wasSatisfied, boolean betterTomorrow){
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
        this.dayRating = dayRating;
        this.hadAnxiety = hadAnxiety;
        this.wasSatisfied = wasSatisfied;
        this.betterTomorrow = betterTomorrow;
    }


}
