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

import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.Utility.QuizGlobalVariables;
import com.example.feelingfinder.R;


public class MoodTrackerActivity extends AppCompatActivity {
    public int rtng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        //Submit button
        //Button submitBtn = findViewById(R.id.moodSubmitBtn);

        //Rating Bar
        RatingBar moodBar = findViewById(R.id.moodBar);

        //Back button
        Button back2ndBtn = findViewById(R.id.back2ndBtn);
        back2ndBtn.setOnClickListener(v -> MoodTrackerActivity.this.onBackPressed());

        //Home button
        Button next2ndBtn = findViewById(R.id.next2ndBtn);

        //mood description
        TextView moodDesc = findViewById(R.id.storyTxt);

        //emoji moodtracker
        ImageButton emojiBtn = findViewById(R.id.emojiBtn);
        ImageButton sadBtn = findViewById(R.id.sadmoobIBtn);
        ImageButton neutralBtn = findViewById(R.id.neutramoodIBtn);
        ImageButton happyBtn = findViewById(R.id.happymoodIBtn);
        ImageButton vhappyBtn1 = findViewById(R.id.veryhappyIBtn);
        ImageButton vsadBtn2 = findViewById(R.id.sadsurpIBtn);
        ImageButton awkBtn = findViewById(R.id.awkwardIBtn);
        ImageButton cryBtn = findViewById(R.id.cryIBtn);
        ImageButton cryBtn2 = findViewById(R.id.sadcryIBtn);
        ImageButton vhappyBtn2 = findViewById(R.id.lazyhappyIBtn);
        TableLayout emojiTbl = findViewById(R.id.emojiTbl);

        //Go to next destination based on a value
        next2ndBtn.setOnClickListener(v -> {
            Intent intent;
            moodBar.setEnabled(false);
            rtng = moodBar.getProgress();
            System.out.println("Val: " + rtng);
            QuizGlobalVariables.todaysQuestions.add(new Question("Emoji", rtng));
            QuizGlobalVariables.hadAnxiety = false;
            if (rtng < 5) {
                intent = new Intent(MoodTrackerActivity.this, FourthQuestionActivity.class);
            } else{
                intent = new Intent(MoodTrackerActivity.this, SecondQuestionActivity.class);
            }
            startActivity(intent);
        });

        //Back function
        back2ndBtn.setOnClickListener(v -> MoodTrackerActivity.this.onBackPressed());

        /*/Submit button function
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String msg=null;
                rtng = (int) moodBar.getRating();
                switch(rtng){
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
        */

        //Rating Bar functions
        moodBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float r, boolean b) {
                moodBar.setRating(ratingBar.getRating());
                //submitBtn.setVisibility(View.VISIBLE);
            }
        });

        //Emoji Tracker Functions
        emojiBtn.setOnClickListener(v ->{
                emojiTbl.setVisibility(View.VISIBLE);
                emojiBtn.setVisibility(View.INVISIBLE);});

        happyBtn.setOnClickListener(v -> {
            moodBar.setRating(8);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(happyBtn.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        awkBtn.setOnClickListener(v -> {
            moodBar.setRating(4);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(awkBtn.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        cryBtn2.setOnClickListener(v -> {
            moodBar.setRating(1);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(cryBtn2.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        cryBtn.setOnClickListener(v -> {
            moodBar.setRating(2);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(cryBtn.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vhappyBtn1.setOnClickListener(v -> {
            moodBar.setRating(9);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(vhappyBtn1.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vsadBtn2.setOnClickListener(v -> {
            moodBar.setRating(3);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(vsadBtn2.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        vhappyBtn2.setOnClickListener(v -> {
            moodBar.setRating(10);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(vhappyBtn2.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        sadBtn.setOnClickListener(v -> {
            moodBar.setRating(3);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(sadBtn.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });

        neutralBtn.setOnClickListener(v -> {
            moodBar.setRating(5);
            emojiTbl.setVisibility(View.INVISIBLE);
            emojiBtn.setImageDrawable(neutralBtn.getDrawable());
            emojiBtn.setVisibility(View.VISIBLE);
        });
    }
}