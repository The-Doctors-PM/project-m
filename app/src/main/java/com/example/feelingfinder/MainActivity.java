package com.example.feelingfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Diary.MyDiaryActivity;
import com.example.feelingfinder.Goals.GoalsActivity;
import com.example.feelingfinder.Profile.ProfileActivity;
import com.example.feelingfinder.Statitics.StatitcsPlaceholderActivity;
import com.example.feelingfinder.Quiz.FirstQuestionActivity;
import com.example.feelingfinder.Utility.FeelingFinder;


public class MainActivity extends AppCompatActivity {

    private ImageButton btn2, btn3, btn4, btn5;
    private FrameLayout dailyQuizbtn;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyQuizbtn = findViewById(R.id.dailyQuizbtn);
        btn2 = findViewById(R.id.notesButton);
        btn3 = findViewById(R.id.goalsButton);
        btn4 = findViewById(R.id.statiticsButton);
        btn5 = findViewById(R.id.profileButton);

        dailyQuizbtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FirstQuestionActivity.class);
            startActivity(intent);
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyDiaryActivity.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GoalsActivity.class);
                startActivity(i);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StatitcsPlaceholderActivity.class);
                startActivity(i);
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        TextView title = findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (count == 5){
                    // Mock Data
                    Database.wipeAllData();
                    Database.importMockData();
                    Toast.makeText(FeelingFinder.getAppContext(), "Mock Data imported!", Toast.LENGTH_LONG).show();
                    count = 0;
                }
            }
        });
    }
}
