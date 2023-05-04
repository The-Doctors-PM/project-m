package com.example.feelingfinder.Profile;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feelingfinder.Dialogs.NotificationPermissionDialog;
<<<<<<< HEAD:app/src/main/java/com/example/feelingfinder/HomepageActivity.java
import com.example.feelingfinder.Diary.MyDiaryActivity;
import com.example.feelingfinder.Goals.GoalsActivity;
import com.example.feelingfinder.MoodTracker.MoodTrackerActivity;
=======
import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
>>>>>>> dev:app/src/main/java/com/example/feelingfinder/Profile/ProfilePlaceholderActivity.java
import com.example.feelingfinder.Utility.FeelingFinder;
import com.example.feelingfinder.databinding.ActivityHomepageBinding;

public class ProfilePlaceholderActivity extends AppCompatActivity {
    private ActivityHomepageBinding binding;
    private final String CHANNEL_ID = "FFChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_homepage);

        // Retrieving some stuff
        Button nowButton;
        nowButton = findViewById(R.id.notifyNowButton);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

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
                //notificationManager.notify(1, builder.build());

            }
        });

<<<<<<< HEAD:app/src/main/java/com/example/feelingfinder/HomepageActivity.java
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

        Button moodTrackerBtn = findViewById(R.id.moodTrackerBtn);
        moodTrackerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeelingFinder.getAppContext(), MoodTrackerActivity.class));
            }
        });
=======
>>>>>>> dev:app/src/main/java/com/example/feelingfinder/Profile/ProfilePlaceholderActivity.java
    }
}
