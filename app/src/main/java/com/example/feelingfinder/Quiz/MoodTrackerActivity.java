package com.example.feelingfinder.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.R;

public class MoodTrackerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();

        // Back button
        // ImageButton backMoodIbt = findViewById(R.id.backMoodIBt);

        //Submit button
        Button submitBtn = findViewById(R.id.moodSubmitBtn);

        //Rating Bar
        RatingBar moodBar = findViewById(R.id.moodBar);

        //Back button function
        //backMoodIbt.setOnClickListener(v -> MoodTrackerActivity.this.onBackPressed());

        //mood description
        TextView moodDesc = findViewById(R.id.storyTxt);

        //emoji moodtracker
        ImageButton emojiBtn = findViewById(R.id.emojiBtn);
        ImageButton sadBtn = findViewById(R.id.sadmoonBtn);
        ImageButton neutralBtn = findViewById(R.id.neutralmoodBtn);
        ImageButton happyBtn = findViewById(R.id.happymoodBtn);
        TableLayout emojiTbl = findViewById(R.id.emojiTbl);

        //Submit button function
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String msg=null;
                int r = (int) moodBar.getRating();
                switch(r){
                    case 1:
                        msg="Sorry to hear that!";
                        break;
                    case 2:
                        msg="Tomorrow is another day full of opportunities!";
                        break;
                    case 3:
                        msg="The glass is half full!";
                        break;
                    case 4:
                        msg="You are on the rise!";
                        break;
                    case 5:
                        msg="You are on top of the world!";
                        break;
                }
                moodDesc.setText(msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                moodBar.setIsIndicator(true);
            }
        });

        //Rating Bar functions
        moodBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float r, boolean b) {
                moodBar.setRating(ratingBar.getRating());
                submitBtn.setVisibility(View.VISIBLE);
            }
        });

        //Emoji Tracker Functions
        emojiBtn.setOnClickListener(v ->{
                emojiTbl.setVisibility(View.VISIBLE);
                emojiBtn.setVisibility(View.INVISIBLE);});

        happyBtn.setOnClickListener(v -> {
            moodBar.setRating(5);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        sadBtn.setOnClickListener(v -> {
            moodBar.setRating(1);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        neutralBtn.setOnClickListener(v -> {
            moodBar.setRating(3);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });
    }
}