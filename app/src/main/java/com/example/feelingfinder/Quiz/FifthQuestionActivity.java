package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.QuizGlobalVariables;

public class FifthQuestionActivity extends AppCompatActivity {

    private Button backBtn5, nextBtn5;
    private SeekBar sBar5;
    public int prog5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_question);

        // Get the buttons and set the OnClickListener

        backBtn5 = findViewById(R.id.backBtn5);
        nextBtn5 = findViewById(R.id.nextBtn5);
        sBar5 = findViewById(R.id.sBar5);

        sBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog5 = sBar5.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        backBtn5.setOnClickListener(v -> FifthQuestionActivity.this.onBackPressed());

        nextBtn5.setOnClickListener(v ->    {
            sBar5.setEnabled(false);
            System.out.println("Val: " + prog5);
            QuizGlobalVariables.todaysQuestions.add(new Question("AnxietyFrequency", prog5));
            Intent intent = new Intent(FifthQuestionActivity.this, SixthQuestionActivity.class);
            startActivity(intent);
        });
    }

}
