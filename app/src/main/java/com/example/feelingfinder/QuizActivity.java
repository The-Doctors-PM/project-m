package com.example.feelingfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    // Declare variables to reference the UI elements
    private EditText feelingEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity using the quiz_activity.xml file
        setContentView(R.layout.activity_quiz);

        // Get references to the UI elements in the layout
        feelingEditText = findViewById(R.id.feelingEditText);
        saveButton = findViewById(R.id.saveButton);

        // Set the onClickListener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the value of the user's feeling from the EditText
                String feeling = feelingEditText.getText().toString();

                // TODO: Save the user's feeling to a database or file

                // Finish this activity and return to the previous one
                finish();
            }
        });
    }
}
