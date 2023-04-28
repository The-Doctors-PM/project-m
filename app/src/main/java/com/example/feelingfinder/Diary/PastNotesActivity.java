package com.example.feelingfinder.Diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.feelingfinder.R;

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

        initRV();

        Button wipeButton = findViewById(R.id.wipeDBNotesButton);
        wipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.wipeNotes();
                finish();
            }
        });
    }
}