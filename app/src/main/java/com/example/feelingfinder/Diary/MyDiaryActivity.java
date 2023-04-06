package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feelingfinder.FeelingFinder;
import com.example.feelingfinder.NotificationActivity;
import com.example.feelingfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.Map;

public class MyDiaryActivity extends AppCompatActivity {

    private LocalDate todayDate = LocalDate.now();
    private String todaysDateString = todayDate.getDayOfMonth() + "-" +
            todayDate.getMonthValue() + "-" +
            todayDate.getYear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        SharedPreferences data = Data.getInstance();

        String savedNote = "";
        System.out.println(todaysDateString);

        if (data.getString(todaysDateString, "").isEmpty()){
            System.out.println("No data today");
            //TODO: popup
        }
        else{
            savedNote = data.getString(todaysDateString, "");
        }
        SharedPreferences.Editor editor = Data.getEditor();

        Button saveButton = findViewById(R.id.saveButton);
        EditText todaysNoteRaw = findViewById(R.id.todaysNote);

        if (!savedNote.isEmpty()){
            todaysNoteRaw.setText(savedNote);
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todaysNote = todaysNoteRaw.getText().toString();
                editor.putString(todaysDateString, todaysNote);
                editor.apply();
            }
        });

        FloatingActionButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeelingFinder.getAppContext(), NotificationActivity.class));
            }
        });

        Button yest = findViewById(R.id.previousDaysButton);
        yest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,?> keys = data.getAll();

                // All entries. Only for debug. We'll use the one below
                /*
                System.out.println("All entries:");
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    System.out.println("map values: " + entry.getKey() + ": " +
                            entry.getValue().toString());
                } */


                // All valid entries only printed here.
                // We'll use this list to retrieve the previous days' diaries
                System.out.println("All valid entries:");
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    if (entry.getValue().toString().isEmpty()){
                        System.out.println("map values: INVALID: EMPTY");
                    }
                    else{
                        System.out.println("map values: " + entry.getKey() + ": " +
                                entry.getValue().toString());
                    }

                }
            }
        });
    }


}