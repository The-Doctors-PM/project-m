package com.example.feelingfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Diary.MyDiaryActivity;
import com.example.feelingfinder.Goals.GoalsActivity;
import com.example.feelingfinder.Profile.ProfileActivity;
import com.example.feelingfinder.Statistics.StatisticsPlaceholderActivity;
import com.example.feelingfinder.Quiz.FirstQuestionActivity;
import com.example.feelingfinder.Utility.FeelingFinder;


public class MainActivity extends AppCompatActivity {

    private ImageButton btn2, btn3, btn4, btn5;
    private FrameLayout dailyQuizbtn;

    private int count=0;
    private static int quizCounter;
    public int getQuizCounter(){return quizCounter;}
    public static void setQuizCounterActive(){quizCounter=1;}
    public void setQuizCounterInactive(){quizCounter=0;}

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
           if(quizCounter==1){
                Toast.makeText(FeelingFinder.getAppContext(), "Congratulations, you have already done your quiz today, come back tomorrow!", Toast.LENGTH_LONG).show();
            } else if(quizCounter==0){
                Intent intent = new Intent(MainActivity.this, FirstQuestionActivity.class);
                startActivity(intent);
            }
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
                Intent i = new Intent(MainActivity.this, StatisticsPlaceholderActivity.class);
                startActivity(i);
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuizCounterInactive();//TODO for the moment its definer here
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

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
