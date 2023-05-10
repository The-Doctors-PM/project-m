package com.example.feelingfinder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    void addNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);


    @Query("SELECT * FROM notes")
    List<Note> getAll();


    @Query("SELECT * FROM notes WHERE id < :today")
    List<Note> getAllPast(int today);

    @Query("SELECT * FROM notes WHERE id < :today AND id > :marker")
    List<Note> getAllPastWeek(int today, int marker);


    @Query("SELECT * FROM notes WHERE id == :today ")
    Note getTodayNote(int today);
}
