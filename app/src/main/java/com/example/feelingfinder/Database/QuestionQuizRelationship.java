package com.example.feelingfinder.Database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class QuestionQuizRelationship {
    @Embedded public Quiz quiz;
    @Relation(
            parentColumn = "id",
            entityColumn = "quizId"
    )
    public List<Question> questions;
}
