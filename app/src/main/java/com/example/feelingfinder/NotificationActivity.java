package com.example.feelingfinder;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.feelingfinder.Alarm.AlarmReceiver;
import com.example.feelingfinder.Dialogs.NotificationPermissionDialog;
import com.example.feelingfinder.databinding.ActivityMainBinding;
import com.example.feelingfinder.databinding.ActivityNotificationBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;
    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        binding = ActivityNotificationBinding.inflate(getLayoutInflater());

        Button nowButton, setAlarmButton;
        nowButton = findViewById(R.id.notifyNowButton);
        Intent intent = new Intent(this, NotificationTappedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Notification Channel creations
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    FeelingFinder.getChannelId(),
                    "Feeling Finder Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationChannel channel2 = new NotificationChannel(
                    FeelingFinder.getChannelId() + "2",
                    "Feeling Finder Channel Scheduled Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            manager.createNotificationChannel(channel2);
        }

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
                        .setChannelId(FeelingFinder.getChannelId());

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FeelingFinder.getAppContext());

                // Shows the user a pop up that informs them to allow notifications
                if (ContextCompat.checkSelfPermission(FeelingFinder.getAppContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("NOTIFICATIONS PERMISSION DENIED");
                    DialogFragment popup = new NotificationPermissionDialog();
                    popup.show(getSupportFragmentManager(), "notificationPopUp");
                }else {
                    notificationManager.notify(1, builder.build());
                }
            }
        });

        setAlarmButton = findViewById(R.id.setAlarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(FeelingFinder.getAppContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("NOTIFICATIONS PERMISSION DENIED");
                    DialogFragment popup = new NotificationPermissionDialog();
                    popup.show(getSupportFragmentManager(), "notificationPopUp");
                }
                else{
                    timePicker = new MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_12H)
                            .setHour(12)
                            .setMinute(0)
                            .setTitleText("Select Alarm Time")
                            .build();
                    timePicker.show(getSupportFragmentManager(), "androidknowledge");
                    timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (timePicker.getHour() > 12){
                                binding.selectTime.setText(
                                        String.format("%02d",(timePicker.getHour()-12)) +":"+ String.format("%02d", timePicker.getMinute())+"PM"
                                );
                            } else {
                                binding.selectTime.setText(timePicker.getHour()+":" + timePicker.getMinute()+ "AM");
                            }
                            calendar = Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                            calendar.set(Calendar.MINUTE, timePicker.getMinute());
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MILLISECOND, 0);
                        }
                    });

                    binding.setAlarm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            Intent intent = new Intent(FeelingFinder.getAppContext(), AlarmReceiver.class);
                            pendingIntent2 = PendingIntent.getBroadcast(FeelingFinder.getAppContext(), 0, intent, 0);
                            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                    AlarmManager.INTERVAL_DAY, pendingIntent2);
                            Toast.makeText(FeelingFinder.getAppContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
                        }
                    });
                    binding.cancelAlarm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(FeelingFinder.getAppContext(), AlarmReceiver.class);
                            pendingIntent2 = PendingIntent.getBroadcast(FeelingFinder.getAppContext(), 0, intent, 0);
                            if (alarmManager == null){
                                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            }
                            alarmManager.cancel(pendingIntent2);
                            Toast.makeText(FeelingFinder.getAppContext(), "Alarm Canceled", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}




