package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feelingfinder.FeelingFinder;
import com.example.feelingfinder.R;

public class MyDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        SharedPreferences data = Data.getInstance();

        String savedNote = "";

        if (data.getString("today date here", "").isEmpty()){
            System.out.println("No data today");
            //TODO: popup
        }
        else{
            savedNote = data.getString("today date here", "");
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
                editor.putString("today date here", todaysNote);
                editor.apply();
            }
        });
    }


}