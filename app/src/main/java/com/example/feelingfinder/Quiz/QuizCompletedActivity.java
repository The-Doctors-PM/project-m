package com.example.feelingfinder.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.Database.Quiz;
import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.QuizGlobalVariables;

public class QuizCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_completed);

        TextView recap = findViewById(R.id.recapQuiz);
        String s = "";
        s = s + "Daily Rating: " + QuizGlobalVariables.dayRating + "\n";
        s = s + "Had Anxiety: " + QuizGlobalVariables.hadAnxiety + "\n";
        s = s + "Was Satisfied: " + QuizGlobalVariables.wasSatisfied + "\n";
        s = s + "Better Tomorrow: " + QuizGlobalVariables.betterTomorrow + "\n\n";

        s = s + "All questions results:\n";
        for (Question q :QuizGlobalVariables.todaysQuestions){
            s = s + q.question + ": " + q.answer + "\n";
        }

        recap.setText(s);


        Button finishQuiz = findViewById(R.id.finishQuiz);
        Button restartQuiz = findViewById(R.id.restartQuiz);
        finishQuiz.setOnClickListener(view -> {
            //TODO: create the quiz and all the DB stuff to save things

            // TODO: Can lock here the test.

            Intent intent = new Intent(QuizCompletedActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        restartQuiz.setOnClickListener(view -> {
            QuizGlobalVariables.initDailyQuestions();
            Intent intent = new Intent(QuizCompletedActivity.this, FirstQuestionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }
}