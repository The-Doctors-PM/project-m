package com.example.feelingfinder.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.R;

public class ThirdQuestionActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        // Get the RatingBar and Next Button views from the layout
        ratingBar = findViewById(R.id.ratingBar);
        nextButton = findViewById(R.id.next_button);

        // Set an on-click listener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user's rating from the RatingBar
                int rating = ratingBar.getProgress();

                // Create an intent to start the FourthQuestionActivity
                Intent intent = new Intent(ThirdQuestionActivity.this, FourthQuestionActivity.class);

                // Pass the user's rating to the FourthQuestionActivity
                intent.putExtra("rating", rating);

                // Start the FourthQuestionActivity
                startActivity(intent);
            }
        });
    }
}
