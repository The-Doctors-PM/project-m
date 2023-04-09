package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.feelingfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class MyDiaryActivity extends AppCompatActivity {

    private LocalDate todayDate = LocalDate.now();
    private String todaysDateString = todayDate.getDayOfMonth() + "-" +
            todayDate.getMonthValue() + "-" +
            todayDate.getYear();

    private String infoTodaysDateString = todayDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US) + " " + todaysDateString;
    private String todaysNote = "";
    private String savedNote = "";

    private EditText todaysNoteRaw;

    private SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        // Finds the "tag" in the bottom-right of the card and sets the current day info in it
        TextView dateTag = findViewById(R.id.dateTag);
        dateTag.setText(infoTodaysDateString);

        // Retrieve the data instance, in order to be able to edit it and save/edit today's note in
        // the storage
        data = Data.getInstance();

        // If today's note is empty, prints it in the log (DEBUG ONLY)
        // Otherwise saves in the variable "savedNote" the current day's note.
        if (data.getString(todaysDateString, "").isEmpty()){
            System.out.println("No data today");
        }
        else{
            savedNote = data.getString(todaysDateString, "");
        }

        // Just retrieves some things
        SharedPreferences.Editor editor = Data.getEditor();
        Button saveButton = findViewById(R.id.saveButton);
        todaysNoteRaw = findViewById(R.id.todaysNote);
        FloatingActionButton backButton = findViewById(R.id.backButton);
        Button yest = findViewById(R.id.previousDaysButton);

        // If savedNote isn't empty, it will be displayed in the note
        if (!savedNote.isEmpty()){
            todaysNoteRaw.setText(savedNote);
        }

        // When clicking the save button, writes in the storage today's note
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todaysNote = todaysNoteRaw.getText().toString();
                editor.putString(todaysDateString, todaysNote);
                editor.apply();
            }
        });

        // When clicking the back button, goes to the Home Activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDiaryActivity.this.onBackPressed();
            }
        });

        // ### DEBUG ONLY ###
        // Clicking the previous days button prints in the log/runner tabs of android studio
        // a list of all the notes which are not empty/invalid, from all days.
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

    // Clicking the back button of the phone, will check if the note has been edited or not.
    // If so, it will alert the user to remember him of saving
    @Override
    public void onBackPressed() {
        savedNote = data.getString(todaysDateString, "");
        if (!savedNote.equals(todaysNoteRaw.getText().toString())){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Discard changes?")
                    .setMessage("Are you sure you want to go back?\n" +
                            "All work done has NOT been saved yet!")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else{
            finish();
        }
    }


}