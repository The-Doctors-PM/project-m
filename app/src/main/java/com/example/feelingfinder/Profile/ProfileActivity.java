package com.example.feelingfinder.Profile;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.SettingsActivity.SettingsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class ProfileActivity extends AppCompatActivity {

    EditText firstName, lastName, email, phone;
    ImageView calendarIV;
    TextView etDate;
    RadioGroup gender;
    RadioButton selectedGender;
    Button saveButton, deleteButton;
    FloatingActionButton backButton, settingsButton;
    SharedPreferences preferences;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initialize views
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        etDate = findViewById(R.id.et_date);
        gender = findViewById(R.id.gender);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        saveButton = findViewById(R.id.saveButtonProfile);
        deleteButton = findViewById(R.id.deleteButtonProfile);
        backButton = findViewById(R.id.toolbar);
        settingsButton = findViewById(R.id.settingsButton);
        calendarIV = findViewById(R.id.calendarButton);
        calendarIV.setClickable(true);

        Calendar calendar  = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

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

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });

        calendarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                        datePickerDialog.show();
            }
        });

        //Save profile data on button click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProfileActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Reset your profile data?")
                        .setMessage("Are you sure you want to reset you profile?\n" +
                                "Nothing will happen to the Diary/Goals/Mood Tracker data.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteProfile();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
    }


    private void loadProfile() {
        //Load saved profile data
        firstName.setText(preferences.getString("firstName", ""));
        lastName.setText(preferences.getString("lastName", ""));
        etDate.setText(preferences.getString("dateOfBirth", etDate.getText().toString()));
        phone.setText(preferences.getString("phone", ""));
        email.setText(preferences.getString("email", ""));
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
        // Save profile data to SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firstName", firstName.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.putString("dateOfBirth", etDate.getText().toString());
        editor.putString("phone", phone.getText().toString());
        String emailInput = email.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Invalid email address");
            return;
        }

        editor.putString("email", emailInput);

        // Check if the user is older than 10 years old
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        String[] dobParts = etDate.getText().toString().split("/");
        int selectedYear = -1;
        int selectedMonth = -1;
        int selectedDay = -1;
        etDate.setFocusable(true);
        etDate.setFocusableInTouchMode(true);
        if (dobParts.length < 2){
            etDate.requestFocus();
            etDate.setError("You must choose a birthday");
            return;
        }
        else{
            selectedYear = Integer.parseInt(dobParts[2]);
            selectedMonth = Integer.parseInt(dobParts[1]);
            selectedDay = Integer.parseInt(dobParts[0]);
        }
        if (selectedDay == -1){
            etDate.requestFocus();
            etDate.setError("You must choose a birthday!");
            return;
        }
        else{
            if (currentYear - selectedYear < 10) {
                etDate.requestFocus();
                etDate.setError("You must be older than 10 years old");
                return;
            } else if (currentYear - selectedYear == 10) {
                if (currentMonth < selectedMonth) {
                    etDate.requestFocus();
                    etDate.setError("You must be older than 10 years old");
                    return;
                } else if (currentMonth == selectedMonth && currentDay < selectedDay) {
                    etDate.requestFocus();
                    etDate.setError("You must be older than 10 years old");
                    return;
                }
            }
        }


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



    private void deleteProfile() {
        // Clear all the saved profile data from SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Clear the EditText fields
        firstName.setText("");
        lastName.setText("");
        etDate.setText("");
        phone.setText("");
        email.setText("");

        // Clear the selected gender radio button
        gender.clearCheck();

        Toast.makeText(this, "Profile Deleted", Toast.LENGTH_SHORT).show();
    }

}

