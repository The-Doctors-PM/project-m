package com.example.feelingfinder.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.QuizGlobalVariables;

public class FourthQuestionActivity extends AppCompatActivity {

    private Button backBtn4, nextBtn4;
    private SeekBar sBar4;
    public int prog4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        // Get the buttons and set the OnClickListener

        backBtn4 = findViewById(R.id.backBtn4);
        nextBtn4 = findViewById(R.id.nextBtn4);
        sBar4 = findViewById(R.id.sBar4);

        sBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog4 = sBar4.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        backBtn4.setOnClickListener(v -> FourthQuestionActivity.this.onBackPressed());

        nextBtn4.setOnClickListener(v ->    {
            sBar4.setEnabled(false);
            Intent intent;
            System.out.println("Val: " + prog4);
            QuizGlobalVariables.todaysQuestions.add(new Question("Anxiety", prog4));
            if(prog4 < 5){
                QuizGlobalVariables.hadAnxiety = false;
                intent = new Intent(FourthQuestionActivity.this, ThirdQuestionActivity.class);
            } else{
                QuizGlobalVariables.hadAnxiety = true;
                intent = new Intent(FourthQuestionActivity.this, FifthQuestionActivity.class);
            }
            startActivity(intent);
        });
    }
    /*
    @Override

    public void onClick(View view) {
        // Check if there is already a button selected
        if (selectedButton != null) {
            selectedButton.setBackgroundColor(Color.parseColor("#e2e4e7"));
        }

        // Set the background color of the clicked button to indicate selection
        view.setBackgroundColor(Color.parseColor("#3CB371"));
        selectedButton = (Button) view;
    }
    */

}
