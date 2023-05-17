package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.feelingfinder.R;

public class FirstQuestionActivity extends AppCompatActivity {

    private SeekBar sBar1;
    private Button nextBtn1, backBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);

        sBar1 = findViewById(R.id.anxiousSB);
        nextBtn1 = findViewById(R.id.nextBtn1);
        backBtn1 = findViewById(R.id.backBtn11);

        backBtn1.setOnClickListener(v -> FirstQuestionActivity.this.onBackPressed());

        nextBtn1.setOnClickListener(view -> {
            int prog1 = sBar1.getProgress();
            // Do something with the progress, such as save it in a global variable
            Intent intent = new Intent(FirstQuestionActivity.this, MoodTrackerActivity.class);
            startActivity(intent);
        });

    }
}
