package com.example.feelingfinder.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "quizzes")
public class Quiz {

    enum Emoji{
        HAPPY,
        AVERAGE,
        SAD
    };

    @PrimaryKey @NonNull
    public int id;

    @NonNull
    public int overall;

    @NonNull
    public Emoji emoji;

    @NonNull
    public int anxiety;

    @NonNull
    public int happiness;



    public Quiz(){
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
        this.overall = 0;
        this.emoji = Emoji.AVERAGE;
        this.anxiety = 0;
        this.happiness = 0;
    }

    public Quiz(int overall, String emoji, int anxiety, int happiness){
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
        this.overall = overall;
        this.emoji = getEmojiFromString(emoji);
        this.anxiety = anxiety;
        this.happiness = happiness;
    }

    public Emoji getEmojiFromString(String emoji){
        if (emoji.equals("Happy")){
            return Emoji.HAPPY;
        } else if (emoji.equals("Average")) {
            return Emoji.AVERAGE;
        }
        else if(emoji.equals("Sad")){
            return Emoji.SAD;
        }
        else {
            System.out.println("Error with emoji!");
            return null;
        }
    }

}
