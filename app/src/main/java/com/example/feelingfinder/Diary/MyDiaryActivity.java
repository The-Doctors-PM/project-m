package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.DateToStringConverter;
import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.Database.NotesDAO;
import com.example.feelingfinder.Utility.FeelingFinder;
import com.example.feelingfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.List;

public class MyDiaryActivity extends AppCompatActivity {

    private LocalDate todayDate;
    private String infoTodaysDateString;
    private int todaysDateInt;
    private String savedNote = "";

    private Note todayNote;

    private EditText todaysNoteRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        // Finds the "tag" in the bottom-right of the card and sets the current day info in it
        TextView dateTag = findViewById(R.id.dateTag);
        dateTag.setText(infoTodaysDateString);

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the notes query
        NotesDAO notesDAO = db.notesDAO();

        // Setup of everything date related
        todayDate = LocalDate.now();
        infoTodaysDateString = DateToStringConverter.transform(todayDate);
        todaysDateInt = DateToStringConverter.dateToInt(todayDate);
        todayNote = notesDAO.getTodayNote(todaysDateInt);


        // If today's note doesn't exist, it creates one.
        // If it instead exists, saves its content in String that will be used later
        if (todayNote == null){
            System.out.println("No note today, creating a new one...");
            notesDAO.addNote(new Note());
            System.out.println("Creation finished!");
            todayNote = notesDAO.getTodayNote(todaysDateInt);
        }
        else{
            savedNote = todayNote.content;
        }

        // Just retrieves some things
        Button saveButton = findViewById(R.id.saveButton);
        todaysNoteRaw = findViewById(R.id.todaysNote);
        FloatingActionButton backButton = findViewById(R.id.backButton);
        Button yest = findViewById(R.id.previousDaysButton);
        ConstraintLayout constraintLayout = findViewById(R.id.diaryCL);

        // If savedNote isn't empty, it will be displayed in the note
        if (!savedNote.isEmpty()){
            todaysNoteRaw.setText(savedNote);
        }

        // When clicking the save button, writes in the storage today's note
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (todaysNoteRaw.getText().toString().isEmpty()){
                    // Creates a "Snack-bar" that informs the user that the data must not be empty
                    Snackbar snackbar = Snackbar.make(constraintLayout, "ERROR, " +
                            "Note must not be empty", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    System.out.println("Update starting");
                    todayNote.content = todaysNoteRaw.getText().toString();
                    notesDAO.updateNote(todayNote);
                    System.out.println("Update completed");
                    // Creates a "Snack-bar" that informs the user that the data has been saved
                    Snackbar snackbar = Snackbar.make(constraintLayout, "SAVED", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

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
        // a list of all the notes from all days.
        // Also redirect to another activity where all past notes are shown
        yest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Note> ln = notesDAO.getAll();
                for (Note n: ln) {
                    System.out.println(n.fullDate + ": " + n.content);
                }

                System.out.println("\n\n---------All past---------");

                List<Note> ln2 = notesDAO.getAllPast(todaysDateInt);
                for (Note n: ln2) {
                    System.out.println(n.fullDate + ": " + n.content);
                }

                startActivity(new Intent(FeelingFinder.getAppContext(), PastNotesActivity.class));

            }
        });


    }

    // Clicking the back button of the phone, will check if the note has been edited or not.
    // If so, it will alert the user to remember him of saving
    @Override
    public void onBackPressed() {
        savedNote = todayNote.content;
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