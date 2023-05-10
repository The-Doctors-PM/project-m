package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.R;

public class SecondQuestionActivity extends AppCompatActivity {

    private SeekBar sBar2;
    private Button nextBtn2,backBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        sBar2 = findViewById(R.id.overallSB);
        nextBtn2 = findViewById(R.id.nextBtn2);
        backBtn2 = findViewById(R.id.backBtn2);

        backBtn2.setOnClickListener(v -> SecondQuestionActivity.this.onBackPressed());

        nextBtn2.setOnClickListener(view -> {
            int progress = sBar2.getProgress();
            // Do something with the progress, such as save it in a global variable
            Intent intent = new Intent(SecondQuestionActivity.this, ThirdQuestionActivity.class);
            startActivity(intent);
        });
    }
}
