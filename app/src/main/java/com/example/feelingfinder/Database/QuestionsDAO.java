package com.example.feelingfinder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionsDAO {

    @Insert
    void addQuestion(Question q);

    @Update
    void updateQuestion(Question q);

    @Delete
    void deleteQuestion(Question q);

    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Query("SELECT * FROM questions WHERE quizId == :quizId")
    List<Question> getQuestionFromQuizId(int quizId);

    @Query("SELECT * FROM questions WHERE id == :qId")
    Question getQuestionFromId(int qId);

}
