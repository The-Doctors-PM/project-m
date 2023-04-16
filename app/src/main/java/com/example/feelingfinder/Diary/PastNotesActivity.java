package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Data;
import com.example.feelingfinder.Database.DateToStringConverter;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.Database.NotesDAO;
import com.example.feelingfinder.R;

import java.time.LocalDate;
import java.util.List;

public class PastNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_notes);

        // Retrieve the Database instance
        AppDatabase db = Data.getAppDatabase();
        // Get access to the goals query
        NotesDAO notesDao = db.notesDAO();
        // List of past notes
        List<Note> ln = notesDao.getAllPast(DateToStringConverter.dateToInt(LocalDate.now()));

        String notesString = "";

        for(Note n: ln){
            notesString = notesString + n.fullDate + ": " + n.content + "\n";
        }

        TextView allNotes = findViewById(R.id.allNotes);
        allNotes.setText(notesString);

        Button wipeButton = findViewById(R.id.wipeDBNotesButton);
        wipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.wipeNotes();
                finish();
            }
        });
    }
}