package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.DateToStringConverter;
import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.Database.NotesDAO;
import com.example.feelingfinder.Goals.GoalsAdapter;
import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.FeelingFinder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.List;

public class PastNotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NotesAdapter notesAdapter;
    private List<Note> ln;

    // ------------------- Recycler View -------------------
    private void initRV(){
        if (recyclerView == null){
            recyclerView = findViewById(R.id.notesRV);
            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        notesAdapter = new NotesAdapter(ln);
        recyclerView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }
    // ----------------- End Recycler View ------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_notes);

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        NotesDAO notesDao = db.notesDAO();
        // List of past notes
        ln = notesDao.getAllPast(DateToStringConverter.dateToInt(LocalDate.now()));
        // Adds today's note to the list
        Note n = notesDao.getTodayNote(DateToStringConverter.dateToInt(LocalDate.now()));
        ln.add(0 ,n);

        // Deletes possible empty notes
        for (Note l : ln){
            if (l.content.isEmpty()){
                System.out.println("Note #" + l.id + " is empty. Deleting it");
                notesDao.deleteNote(l);
            }
        }

        initRV();

        Button wipeButton = findViewById(R.id.wipeDBNotesButton);
        wipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.wipeNotes();
                startActivity(new Intent(FeelingFinder.getAppContext(), MainActivity.class));
            }
        });

        FloatingActionButton backButton = findViewById(R.id.backButtonToDiary);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}