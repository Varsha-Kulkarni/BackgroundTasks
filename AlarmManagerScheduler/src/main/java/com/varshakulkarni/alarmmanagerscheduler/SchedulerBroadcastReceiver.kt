package com.varshakulkarni.alarmmanagerscheduler

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import com.varshakulkarni.alarmmanagerscheduler.Constants.Notifications.CHANNEL_ID
import com.varshakulkarni.alarmmanagerscheduler.Constants.Notifications.CHANNEL_NAME
import java.util.Calendar
import java.util.Date


class SchedulerBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val now = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 1)
        }
        val alarmManager = context.getSystemService(
            Context.ALARM_SERVICE
        ) as AlarmManager

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            Constants.Alarm.ALARM_ID,
            Intent(context, SchedulerBroadcastReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            now.timeInMillis,
            pendingIntent
        )
        Log.d(TAG, "Alarm has been rescheduled")

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder: Notification.Builder?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )

            notificationBuilder = Notification.Builder(context, CHANNEL_ID)
                .setContentTitle("New Notification")
                .setAutoCancel(true)
                .setContentText("New Notification Alert!")
                .setPriority(Notification.PRIORITY_MAX)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        } else {
            notificationBuilder = Notification.Builder(context)
        }
        val notification = notificationBuilder?.setContentTitle("New Notification")
            ?.setAutoCancel(true)
            ?.setSmallIcon(R.drawable.ic_launcher_background)
            ?.setContentText("New Notification Alert!")?.build()
        val id = ((Date().time / 1000L) % Int.MAX_VALUE).toInt()
        notificationManager.notify(id, notification)
    }

    companion object {
        const val TAG = "SchedulerBroadcastReceiver"
    }
}