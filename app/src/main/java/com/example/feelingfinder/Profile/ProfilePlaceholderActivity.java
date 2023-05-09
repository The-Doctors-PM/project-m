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
import com.example.feelingfinder.ExportPDF.ExportActivity;
import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.SettingsActivity.SettingsActivity;
import com.example.feelingfinder.Utility.FeelingFinder;

public class ProfilePlaceholderActivity extends AppCompatActivity {
    private final String CHANNEL_ID = "FFChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_placeholder);

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
                notificationManager.notify(1, builder.build());

            }
        });


        // Go to export
        Button goToExport = findViewById(R.id.goToExport);
        goToExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeelingFinder.getAppContext(), ExportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // Go to Leonardo's Settings
        Button goToSetting = findViewById(R.id.goToSettings);
        goToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeelingFinder.getAppContext(), SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
