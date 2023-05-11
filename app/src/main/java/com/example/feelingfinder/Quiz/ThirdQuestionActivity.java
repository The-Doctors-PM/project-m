package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.R;

public class ThirdQuestionActivity extends AppCompatActivity {

    private SeekBar sBar3;
    private Button nextBtn3,backBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        // Get the RatingBar and Next Button views from the layout
        sBar3 = findViewById(R.id.happySB);
        nextBtn3 = findViewById(R.id.nextBtn3);
        backBtn3 = findViewById(R.id.backBtn3);

        backBtn3.setOnClickListener(v -> ThirdQuestionActivity.this.onBackPressed());

        nextBtn3.setOnClickListener(view -> {
            int prog3 = sBar3.getProgress();
            // Do something with the progress, such as save it in a global variable
            Intent intent = new Intent(ThirdQuestionActivity.this, FourthQuestionActivity.class);
            startActivity(intent);

        });
    }
}
