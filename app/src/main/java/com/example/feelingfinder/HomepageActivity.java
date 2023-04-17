package com.example.feelingfinder;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feelingfinder.Dialogs.NotificationPermissionDialog;
import com.example.feelingfinder.Diary.MyDiaryActivity;
import com.example.feelingfinder.Goals.GoalsActivity;
import com.example.feelingfinder.Utility.FeelingFinder;
import com.example.feelingfinder.databinding.ActivityHomepageBinding;

public class HomepageActivity extends AppCompatActivity {
    private ActivityHomepageBinding binding;
    private String CHANNEL_ID = "FFChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_homepage);

        // Retrieving some stuff
        Button nowButton, tenButton;
        nowButton = findViewById(R.id.notifyNowButton);
        Intent intent = new Intent(this, NotificationTappedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // If Device is Android Oreo or more recent, creates the notification channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Feeling Finder Channel",
                    NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Create a notification on button click
        nowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(FeelingFinder.getAppContext(), FeelingFinder.getChannelId())
                        .setSmallIcon(R.drawable.logo2nobg)
                        .setContentTitle("First notification")
                        .setContentText("Example text just for testing purposes")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent) //TODO: Update with questionnaire activity when ready
                        .setChannelId(CHANNEL_ID);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FeelingFinder.getAppContext());
                if (ContextCompat.checkSelfPermission(FeelingFinder.getAppContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("NOTIFICATIONS PERMISSION DENIED");
                    // Shows the user a pop up that informs them to allow notifications
                    DialogFragment popup = new NotificationPermissionDialog();
                    popup.show(getSupportFragmentManager(), "notificationPopUp");
                }
                notificationManager.notify(1, builder.build());

            }
        });

        // Button that goes to Diary
        Button diaryButton = findViewById(R.id.goToDiaryButton);
        diaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeelingFinder.getAppContext(), MyDiaryActivity.class));
            }
        });


        // Button that goes to Goals
        Button goalsButton = findViewById(R.id.goalsButton);
        goalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeelingFinder.getAppContext(), GoalsActivity.class));
            }
        });
    }
}
