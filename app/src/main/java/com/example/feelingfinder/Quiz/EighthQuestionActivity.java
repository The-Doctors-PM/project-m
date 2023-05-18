package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.R;

public class EighthQuestionActivity extends AppCompatActivity {

    private Button backBtn8, nextBtn8;
    private SeekBar sBar8;
    private int prog8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_question);

        // Get the buttons and set the OnClickListener

        backBtn8 = findViewById(R.id.backBtn8);
        nextBtn8 = findViewById(R.id.nextBtn8);
        sBar8 = findViewById(R.id.sBar8);

        sBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog8 = sBar8.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        backBtn8.setOnClickListener(v -> EighthQuestionActivity.this.onBackPressed());

        nextBtn8.setOnClickListener(v ->    {
            Intent intent = new Intent(EighthQuestionActivity.this, NinthQuestionActivity.class);
            startActivity(intent);
        });
    }

}
