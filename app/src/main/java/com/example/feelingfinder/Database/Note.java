package com.example.feelingfinder.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey @NonNull
    public int id;
    @NonNull
    public String fullDate;

    @NonNull
    public String content;

    @NonNull
    public int year;

    @NonNull
    public int month;

    @NonNull
    public int day;

    @NonNull
    public String dayOfTheWeek;




    public Note(){
        this.fullDate = DateToStringConverter.transform(LocalDate.now());
        this.content = "";
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonthValue();
        this.day = LocalDate.now().getDayOfMonth();
        this.dayOfTheWeek = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
    }

    public Note(LocalDate date){
        this.fullDate = DateToStringConverter.transform(date);
        this.content = "";
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.dayOfTheWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        this.id = DateToStringConverter.dateToInt(date);
    }

    public Note(@NonNull String content){
        this.fullDate = DateToStringConverter.transform(LocalDate.now());
        this.content = content;
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonthValue();
        this.day = LocalDate.now().getDayOfMonth();
        this.dayOfTheWeek = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        this.id = DateToStringConverter.dateToInt(LocalDate.now());
    }

    public Note(LocalDate date, @NonNull String content){
        this.fullDate = DateToStringConverter.transform(date);
        this.content = content;
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.dayOfTheWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        this.id = DateToStringConverter.dateToInt(date);
    }

    public Note (int date, @NonNull String content){
        this.id = date;
        this.content = content;
        String dateS = "" + date;
        this.year = Integer.parseInt(dateS.substring(0,4));
        this.month = Integer.parseInt(dateS.substring(4,6));
        this.day = Integer.parseInt(dateS.substring(6,8));
        this.dayOfTheWeek = "Neptunday";
        this.fullDate = this.dayOfTheWeek + " " + this.day + "/" + this.month + "/" + this.year;
    }

}
