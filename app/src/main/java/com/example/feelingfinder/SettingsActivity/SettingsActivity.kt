package com.example.feelingfinder.SettingsActivity

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.feelingfinder.Notifications.*
import com.example.feelingfinder.R
import com.example.feelingfinder.databinding.ActivitySettingsBinding
import org.w3c.dom.Text
import java.sql.Time
import java.time.LocalTime
import java.util.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)

        // button which will let the user edit the mood tracker's notification time
        val moodTrackerEditButton = findViewById<Button>(R.id.moodTrackerEditTimeButton)
        createNotificationChannel()
        moodTrackerEditButton.setOnClickListener { scheduleNotification() }


        // button which lets the user export his/her diary
        val dataExportButton = findViewById<Button>(R.id.dataExportButton)
    }

    private fun scheduleNotification()
    {
        val intent = Intent(applicationContext, Notification::class.java)
        val title = "Daily Mood Tracker"
        val message = "It's time for your daily mood tracker!"
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                notificationID,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()    // get time from user input from interface

        val showCurrentTimeSet = findViewById<TextView>(R.id.moodTrackerCurrentNotifTimeSet)
        val currentTimeSetText = "Current mood tracker notification time set to: " + timeString(time)
        showCurrentTimeSet.text = currentTimeSetText

        // schedule notification daily
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                time,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
        showAlert(time)
    }

    private fun showAlert(time: Long)
    {
        AlertDialog.Builder(this)
                .setTitle("Notification Scheduled")
                .setMessage("Daily Mood Tracker reminder set to ${timeString(time)}")
                .setPositiveButton("Ok"){_,_ ->}
                .show()
    }

    private fun getTime(): Long
    {
        val minute = settingsBinding.moodTrackerEditTimePicker.minute
        val hour = settingsBinding.moodTrackerEditTimePicker.hour

        val time = LocalTime.of(hour, minute, 0)
        return time.toNanoOfDay() / 1000000
    }

    private fun timeString(time: Long): String {
        val hours = (time / (1000 * 60 * 60)) % 24
        val minutes = (time / (1000 * 60)) % 60

        return String.format("%02d:%02d", hours, minutes)
    }

    private fun createNotificationChannel()
    {
        val name = "Mood Tracker"
        val desc = "Mood tracker notification channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}