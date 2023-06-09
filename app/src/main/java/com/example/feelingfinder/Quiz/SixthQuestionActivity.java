package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.QuizGlobalVariables;

public class SixthQuestionActivity extends AppCompatActivity {

    private Button backBtn6, nextBtn6;
    private SeekBar sBar6;
    public int prog6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_question);

        // Get the buttons and set the OnClickListener

        backBtn6 = findViewById(R.id.backBtn6);
        nextBtn6 = findViewById(R.id.nextBtn6);
        sBar6 = findViewById(R.id.sBar6);

        sBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog6 = sBar6.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        backBtn6.setOnClickListener(v -> {
            QuizGlobalVariables.todaysQuestions.remove(QuizGlobalVariables.counter--);
            SixthQuestionActivity.this.onBackPressed();
        });

        nextBtn6.setOnClickListener(v ->{
            sBar6.setEnabled(false);
            System.out.println("Val: " + prog6);
            QuizGlobalVariables.todaysQuestions.add(new Question("Breathing", prog6));
            QuizGlobalVariables.counter++;
            Intent intent = new Intent(SixthQuestionActivity.this, SeventhQuestionActivity.class);
            startActivity(intent);
        });


    }
}
