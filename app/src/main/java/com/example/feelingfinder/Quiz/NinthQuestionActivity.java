package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;

import kotlin.random.Random;

public class NinthQuestionActivity extends AppCompatActivity {

    private Button backBtn9, nextBtn9;
    private SeekBar sBar9;
    private String msg9;
    Random rnd = new Random() {
        @Override
        public int nextBits(int i) {
            return 0;
        }
    };
    public int prog9, rndn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth_question);

        // Get the buttons and set the OnClickListener

        backBtn9 = findViewById(R.id.backBtn9);
        nextBtn9 = findViewById(R.id.nextBtn9);
        sBar9 = findViewById(R.id.sBar9);
        sBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog9 = sBar9.getProgress();
                rndn = rnd.nextInt(1, 6);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                switch (rndn) {
                    case 6:
                        msg9 = "Think about positive things";
                        break;
                    case 1:
                        msg9 = "Talk to people you trust";
                        break;
                    case 2:
                        msg9 = "Go for a walk in the park";
                        break;
                    case 3:
                        msg9 = "Call and talk to your loved ones";
                        break;
                    case 4:
                        msg9 = "Try to do some meditation or yoga";
                        break;
                    case 5:
                        msg9 = "Eat one of your favorite meals";
                        break;
                }
                Toast.makeText(getApplicationContext(), msg9, Toast.LENGTH_LONG).show();
            }
        });

        backBtn9.setOnClickListener(v -> NinthQuestionActivity.this.onBackPressed());

        nextBtn9.setOnClickListener(v ->    {
            sBar9.setEnabled(false);
            MainActivity.setQuizCounterActive();
            Intent intent = new Intent(NinthQuestionActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}
