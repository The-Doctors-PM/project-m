package com.example.feelingfinder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Utility.FeelingFinder;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the database if it exists otherwise create a new one
        Database.buildAppDatabase();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // If Device is Android Oreo or more recent, creates the notification channels
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            FeelingFinder.getChannelId(),
                            "Feeling Finder Channel",
                            NotificationManager.IMPORTANCE_HIGH);

                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }

                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 2000);


    }

}
