package com.example.feelingfinder.Alarm;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.feelingfinder.FeelingFinder;
import com.example.feelingfinder.NotificationTappedActivity;
import com.example.feelingfinder.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent questionnaireActivity = new Intent(FeelingFinder.getAppContext(), NotificationTappedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(FeelingFinder.getAppContext(), 0, questionnaireActivity, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(FeelingFinder.getAppContext(), FeelingFinder.getChannelId())
                .setSmallIcon(R.drawable.logo2nobg)
                .setContentTitle("Second notification")
                .setContentText("Time for your daily questionnaire")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setChannelId(FeelingFinder.getChannelId());


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(FeelingFinder.getAppContext());
        if (ActivityCompat.checkSelfPermission(FeelingFinder.getAppContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(123, builder.build());
    }
}
