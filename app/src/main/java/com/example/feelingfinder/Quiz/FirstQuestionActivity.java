package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.Utility.QuizGlobalVariables;
import com.example.feelingfinder.R;

public class FirstQuestionActivity extends AppCompatActivity {

    public SeekBar sBar1;
    private ImageView imageV1;
    private Button nextBtn1, backBtn1;
    public int prog;
    void dissSeekBar(){
        sBar1.setClickable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);

        QuizGlobalVariables.dayRating = -1;

        sBar1 = findViewById(R.id.anxiousSB);
        nextBtn1 = findViewById(R.id.nextBtn1);
        backBtn1 = findViewById(R.id.backBtn11);
        imageV1 = findViewById(R.id.imageV1);

        backBtn1.setOnClickListener(v -> FirstQuestionActivity.this.onBackPressed());

        sBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = sBar1.getProgress();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        nextBtn1.setOnClickListener(view -> {
            // Do something with the progress, such as save it in a global variable
            sBar1.setEnabled(false);
            System.out.println("Prog: " + this.prog);

            QuizGlobalVariables.initDailyQuestions();
            QuizGlobalVariables.dayRating = prog;

            Intent intent = new Intent(FirstQuestionActivity.this, MoodTrackerActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FirstQuestionActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
