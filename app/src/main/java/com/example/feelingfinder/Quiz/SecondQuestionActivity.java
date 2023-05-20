package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;

public class SecondQuestionActivity extends AppCompatActivity {

    private SeekBar sBar2;
    private Button nextBtn2,backBtn2;
    int val;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        sBar2 = findViewById(R.id.overallSB);
        nextBtn2 = findViewById(R.id.nextBtn2);
        backBtn2 = findViewById(R.id.backBtn2);

        backBtn2.setOnClickListener(v -> SecondQuestionActivity.this.onBackPressed());

        sBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = sBar2.getProgress();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //val = sBar2.getProgress();
                if(val>=5) {
                    switch (val) {
                        case 5:
                            msg = "Sometimes maybe good, sometimes maybe not!";
                            break;
                        case 6:
                            msg = "Tomorrow is another day full of opportunities!";
                            break;
                        case 7:
                            msg = "If you need a break, just take it.";
                            break;
                        case 8:
                            msg = "You are on the rise!";
                            break;
                        case 9:
                            msg = "Nobody can stop you!";
                            break;
                        case 10:
                            msg = "You are on top of the world!";
                            break;
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        nextBtn2.setOnClickListener(view -> {
            if(val < 5) {
                // Do something with the progress, such as save it in a global variable
                Intent intent = new Intent(SecondQuestionActivity.this, ThirdQuestionActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(SecondQuestionActivity.this, MainActivity.class);
                startActivity(intent);
            }
            //TODO Lock the mood activity for the day
        });
    }
}
