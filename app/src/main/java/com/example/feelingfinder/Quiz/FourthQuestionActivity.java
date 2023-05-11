package com.example.feelingfinder.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;

public class FourthQuestionActivity extends AppCompatActivity {

    private Button backBtn4, nextBtn4;
    private RadioButton betterRb,sameRb,worseRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        // Get the buttons and set the OnClickListener

        backBtn4 = findViewById(R.id.backBtn4);
        nextBtn4 = findViewById(R.id.nextBtn4);

        backBtn4.setOnClickListener(v -> FourthQuestionActivity.this.onBackPressed());
        nextBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Store the user's rating and move to the next question/activity
                startActivity(new Intent(FourthQuestionActivity.this, MoodTrackerActivity.class));
            }
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
