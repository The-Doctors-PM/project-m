package com.example.feelingfinder.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.feelingfinder.MainActivity;
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

        //Back button
        Button backBtn5 = findViewById(R.id.backBtn5);
        backBtn5.setOnClickListener(v -> MoodTrackerActivity.this.onBackPressed());

        //Home button
        Button homeBtn = findViewById(R.id.homeBtn);

        //mood description
        TextView moodDesc = findViewById(R.id.storyTxt);

        //emoji moodtracker
        ImageButton emojiBtn = findViewById(R.id.emojiBtn);
        ImageButton sadBtn = findViewById(R.id.sadmoobIBtn);
        ImageButton neutralBtn = findViewById(R.id.neutramoodIBtn);
        ImageButton happyBtn = findViewById(R.id.happymoodIBtn);
        ImageButton vhappyBtn1 = findViewById(R.id.veryhappyIBtn);
        ImageButton vsadBtn1 = findViewById(R.id.sadcryIBtn);
        ImageButton vsadBtn2 = findViewById(R.id.sadsurpIBtn);
        ImageButton awkBtn = findViewById(R.id.awkwardIBtn);
        ImageButton cryBtn = findViewById(R.id.cryIBtn);
        ImageButton cryBtn2 = findViewById(R.id.sadcryIBtn);
        ImageButton vhappyBtn2 = findViewById(R.id.lazyhappyIBtn);
        TableLayout emojiTbl = findViewById(R.id.emojiTbl);

        //Return to main activity
        homeBtn.setOnClickListener(v ->
            startActivity(new Intent(MoodTrackerActivity.this, MainActivity.class)));

        //Back function
        backBtn5.setOnClickListener(v -> MoodTrackerActivity.this.onBackPressed());

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
                        msg="Have you had a nice cup of chocolate today?";
                        break;
                    case 3:
                        msg="It can always be worse!";
                        break;
                    case 4:
                        msg="Even the best have days like this.";
                        break;
                    case 5:
                        msg="Sometimes maybe good, sometimes maybe shit!";
                        break;
                    case 6:
                        msg="Tomorrow is another day full of opportunities!";
                        break;
                    case 7:
                        msg="If you need a break, just take it.";
                        break;
                    case 8:
                        msg="You are on the rise!";
                        break;
                    case 9:
                        msg="Nobody can stop you!";
                        break;
                    case 10:
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
            moodBar.setRating(8);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setBackground(happyBtn.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        awkBtn.setOnClickListener(v -> {
            moodBar.setRating(4);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setBackground(awkBtn.get());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        cryBtn2.setOnClickListener(v -> {
            moodBar.setRating(1);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        cryBtn.setOnClickListener(v -> {
            moodBar.setRating(2);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vhappyBtn1.setOnClickListener(v -> {
            moodBar.setRating(9);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vsadBtn2.setOnClickListener(v -> {
            moodBar.setRating(3);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vhappyBtn2.setOnClickListener(v -> {
            moodBar.setRating(10);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vsadBtn1.setOnClickListener(v -> {
            moodBar.setRating(4);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        sadBtn.setOnClickListener(v -> {
            moodBar.setRating(3);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });

        neutralBtn.setOnClickListener(v -> {
            moodBar.setRating(5);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setVisibility(View.VISIBLE);
        });
    }
}