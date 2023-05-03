package com.example.feelingfinder.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;

public class FourthQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button verySadButton, sadButton, happyButton, veryHappyButton, nextButton;
    private Button selectedButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        // Get the buttons and set the OnClickListener
        verySadButton = findViewById(R.id.button_very_sad);
        verySadButton.setOnClickListener(this);

        sadButton = findViewById(R.id.button_sad);
        sadButton.setOnClickListener(this);

        happyButton = findViewById(R.id.button_happy);
        happyButton.setOnClickListener(this);

        veryHappyButton = findViewById(R.id.button_very_happy);
        veryHappyButton.setOnClickListener(this);

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Store the user's rating and move to the next question/activity
                startActivity(new Intent(FourthQuestionActivity.this, MainActivity.class));
            }
        });
    }

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
}
