package com.example.feelingfinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Database;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the database if it exists otherwise create a new one
        Database.buildAppDatabase();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, HomepageActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 2000);


    }

}
