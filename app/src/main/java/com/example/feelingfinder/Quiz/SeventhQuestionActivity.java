package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.QuizGlobalVariables;

public class SeventhQuestionActivity extends AppCompatActivity {

    private Button backBtn7, nextBtn7;
    private SeekBar sBar7;
    private int prog7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_question);

        // Get the buttons and set the OnClickListener

        backBtn7 = findViewById(R.id.backBtn7);
        nextBtn7 = findViewById(R.id.nextBtn7);
        sBar7 = findViewById(R.id.sBar7);

        sBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog7 = sBar7.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        backBtn7.setOnClickListener(v -> SeventhQuestionActivity.this.onBackPressed());

        nextBtn7.setOnClickListener(v ->    {
            System.out.println("Val: " + prog7);
            QuizGlobalVariables.todaysQuestions.add(new Question("Heart", prog7));
            Intent intent = new Intent(SeventhQuestionActivity.this, EighthQuestionActivity.class);
            startActivity(intent);
        });
    }

}
