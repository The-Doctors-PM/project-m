package com.example.feelingfinder.SettingsActivity

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.feelingfinder.Notifications.*
import com.example.feelingfinder.R
import com.example.feelingfinder.databinding.ActivitySettingsBinding
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

        // button which lets the user upload his/her own profile picture or avatar
        val uploadProfilePicture = findViewById<Button>(R.id.profilePictureUploadButton)

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
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
        )
        showAlert(time, title, message)
    }

    private fun showAlert(time: Long, title: String, message: String)
    {
        val date = Date(time)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
                .setTitle("Notification Scheduled")
                .setMessage(
                        "Title: " + title +
                                "\nMessage: " + message + ' ' + timeFormat.format(date))
                .setPositiveButton("Okay"){_,_ ->}
                .show()
    }

    private fun getTime(): Long
    {
        val minute = settingsBinding.moodTrackerEditTimePicker.minute
        val hour = settingsBinding.moodTrackerEditTimePicker.hour

        val calendar = Calendar.getInstance()
        calendar.set(hour, minute)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel()
    {
        val name = "Mood Tracker Channel"
        val desc = "Mood tracker notification channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}