package com.example.feelingfinder.SettingsActivity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // button which will let the user edit the mood tracker's notification time
        Button moodTrackerEditButton = findViewById(R.id.moodTrackerEditTimeButton);

        // button which lets the user upload his/her own profile picture or avatar
        Button uploadProfilePicture = findViewById(R.id.profilePictureUploadButton);

        // button which lets the user export his/her diary
        Button dataExportButton = findViewById(R.id.dataExportButton);
    }
}
