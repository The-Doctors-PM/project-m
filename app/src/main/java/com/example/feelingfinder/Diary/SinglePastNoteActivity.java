package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.Database.NotesDAO;
import com.example.feelingfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SinglePastNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_past_note);

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        NotesDAO notesDao = db.notesDAO();
        // Retrieve the ID passed to query the note.
        int id = getIntent().getIntExtra("id", 0);
        // Get the note with passed id
        Note note = notesDao.getTodayNote(id);

        // Get some stuff with the findView
        TextView content, date;
        date = findViewById(R.id.singleNoteDate);
        content = findViewById(R.id.singleNoteContent);

        // Update the stuff with the correct data
        date.setText(note.fullDate);
        content.setText(note.content);

        FloatingActionButton backButton = findViewById(R.id.backButtonToNotesList);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}