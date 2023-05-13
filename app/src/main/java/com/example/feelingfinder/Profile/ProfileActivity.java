package com.example.feelingfinder.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.SettingsActivity.SettingsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProfileActivity extends AppCompatActivity {

    EditText firstName, lastName;
    TextView dateOfBirth;
    RadioGroup gender;
    RadioButton selectedGender;
    Button saveButton;
    FloatingActionButton backButton;
    SharedPreferences preferences;

    Button toSettingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initialize views
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        gender = findViewById(R.id.gender);
        saveButton = findViewById(R.id.saveButtonProfile);
        backButton = findViewById(R.id.toolbar);

        //Get SharedPreferences instance
        preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

        //Load saved profile data
        loadProfile();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Save profile data on button click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        toSettingsButton = findViewById(R.id.profileToSettings);
        toSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    private void loadProfile() {
        //Load saved profile data
        firstName.setText(preferences.getString("firstName", ""));
        lastName.setText(preferences.getString("lastName", ""));
        dateOfBirth.setText(preferences.getString("dateOfBirth", dateOfBirth.getText().toString()));
        String savedGender = preferences.getString("gender", "");
        if (savedGender.equals("Male")) {
            gender.check(R.id.male);
        } else if (savedGender.equals("Female")) {
            gender.check(R.id.female);
        } else if (savedGender.equals("Other")) {
            gender.check(R.id.other);
        }

    }

    private void saveProfile() {
        //Save profile data to SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firstName", firstName.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.putString("dateOfBirth", dateOfBirth.getText().toString());
        int selectedGenderId = gender.getCheckedRadioButtonId();
        if (selectedGenderId != -1) {
            selectedGender = findViewById(selectedGenderId);
            editor.putString("gender", selectedGender.getText().toString());
        } else {
            editor.putString("gender", "");
        }
        editor.apply();
        Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();
    }

}

