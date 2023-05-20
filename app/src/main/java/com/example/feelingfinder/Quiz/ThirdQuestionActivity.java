package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.Quiz.FirstQuestionActivity;
import com.example.feelingfinder.R;
import kotlin.random.Random;
import kotlin.random.URandomKt;

public class ThirdQuestionActivity extends AppCompatActivity {

    public SeekBar sBar3;
    private Button nextBtn3,backBtn3;
    public int prog3, rndn;
    Random rnd = new Random() {
        @Override
        public int nextBits(int i) {
            return 0;
        }
    };
    String msg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        // Get the RatingBar and Next Button views from the layout
        sBar3 = findViewById(R.id.happySB);
        FirstQuestionActivity fqa = new FirstQuestionActivity();

        nextBtn3 = findViewById(R.id.nextBtn3);
        backBtn3 = findViewById(R.id.backBtn3);

        backBtn3.setOnClickListener(v -> ThirdQuestionActivity.this.onBackPressed());

        sBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog3 = sBar3.getProgress();
                rndn = rnd.nextInt(1, 6);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(prog3 <= 5) {
                    switch (rndn) {
                        case 6:
                            msg3 = "Think about positive things";
                            break;
                        case 1:
                            msg3 = "Talk to people you trust";
                            break;
                        case 2:
                            msg3 = "Go for a walk in the park";
                            break;
                        case 3:
                            msg3 = "Call and talk to your loved ones";
                            break;
                        case 4:
                            msg3 = "Try to do some meditation or yoga";
                            break;
                        case 5:
                            msg3 = "Eat one of your favorite meals";
                            break;
                    }
                    Toast.makeText(getApplicationContext(), msg3, Toast.LENGTH_LONG).show();
                }
                switch(prog3){
                    case 5:
                        msg3="Sometimes maybe good, sometimes maybe shit.";
                        break;
                    case 6:
                        msg3="Tomorrow is another day full of opportunities!";
                        break;
                    case 7:
                        msg3="If you need a break, just take it, its ok not to be awesome everyday.";
                        break;
                    case 8:
                        msg3="You are on the rise!";
                        break;
                    case 9:
                        msg3="Nobody can stop you!";
                        break;
                    case 10:
                        msg3="You are on top of the world!";
                        break;
                }
                Toast.makeText(getApplicationContext(), msg3, Toast.LENGTH_LONG).show();
            }
        });

        nextBtn3.setOnClickListener(view -> {
                // Do something with the progress, such as save it in a global variable
                sBar3.setEnabled(false);
                MainActivity.setQuizCounterActive();
                Intent intent = new Intent(ThirdQuestionActivity.this, MainActivity.class);
                startActivity(intent);

        });
    }
}
