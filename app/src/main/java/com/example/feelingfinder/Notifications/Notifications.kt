package com.example.feelingfinder.Notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.feelingfinder.Quiz.FirstQuestionActivity
import com.example.feelingfinder.Quiz.MoodTrackerActivity
import com.example.feelingfinder.R

const val notificationID = 1
const val channelID = "Mood tracker"
const val titleExtra = "Daily Mood Tracker"
const val messageExtra = "It's time for the daily mood tracker!"

class Notification : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {
        var resultIntent = Intent(context, FirstQuestionActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val resultPendingIntent = PendingIntent.getActivity(
            context,
            0,
            resultIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(intent.getStringExtra(titleExtra))
                .setContentText(intent.getStringExtra(messageExtra))
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .build()

        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }

}