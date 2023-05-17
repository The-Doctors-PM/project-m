package com.example.feelingfinder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuizDAO {

    @Insert
    void addQuiz(Quiz quiz);

    @Update
    void updateQuiz(Quiz quiz);

    @Delete
    void deleteQuiz(Quiz quiz);

    @Query("SELECT * FROM quizzes")
    List<Quiz> getAll();

    @Query("SELECT * FROM quizzes WHERE id < :today")
    List<Quiz> getAllPast(int today);

    @Query("SELECT * FROM quizzes WHERE id < :today AND id > :marker")
    List<Quiz> getAllPastWeek(int today, int marker);

    @Query("SELECT * FROM quizzes WHERE id == :today")
    Quiz getTodayQuiz(int today);
/*
    @Transaction
    @Query("SELECT * FROM quizzes WHERE id == :quizId")
    List<QuestionQuizRelationship> getQuizQuestions(int quizId);
*/
}
