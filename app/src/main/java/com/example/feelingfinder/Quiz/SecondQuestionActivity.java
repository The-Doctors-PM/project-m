package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.R;

public class SecondQuestionActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        seekBar = findViewById(R.id.overallSB);
        nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(view -> {
            int progress = seekBar.getProgress();
            // Do something with the progress, such as save it in a global variable
            Intent intent = new Intent(SecondQuestionActivity.this, MoodTrackerActivity.class);
            startActivity(intent);
        });
    }
}
