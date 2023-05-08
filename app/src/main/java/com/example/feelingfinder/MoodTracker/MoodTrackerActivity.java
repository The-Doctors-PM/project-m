package com.example.feelingfinder.MoodTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
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
        ImageButton backMoodIbt = findViewById(R.id.backMoodIBt);

        //Submit button
        Button submitBtn = findViewById(R.id.moodSubmitBtn);

        //Rating Bar
        RatingBar moodBar = findViewById(R.id.moodBar);

        //Back button function
        backMoodIbt.setOnClickListener(v ->
            MoodTrackerActivity.this.onBackPressed());

        //Submit button function
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totStars = "Total Stars:: " + moodBar.getNumStars();
                String rating = "Rating :: " + moodBar.getRating();
                Toast.makeText(getApplicationContext(), totStars + "\n" + rating, Toast.LENGTH_LONG).show();

            }
        });

        //Rating Bar functions

        //moodBar.setOnClickListener();
        moodBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float r, boolean b) {
                String msg=null;
                int v = (int) ratingBar.getRating();
                //int v = (int) r;
                switch(v){
                    case 1:
                        msg="Sorry to hear that!";
                        break;
                    case 2:
                        msg="Tomorrow is another day full o opportunities!";
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
                moodBar.setRating(ratingBar.getRating());
                Toast.makeText(MoodTrackerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}