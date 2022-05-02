package com.varshakulkarni.alarmmanagerscheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.varshakulkarni.common.NotificationUtil
import java.util.*

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

        NotificationUtil.createNotification(context, R.drawable.ic_launcher_background)

    }

    companion object {
        const val TAG = "SchedulerBroadcastReceiver"
    }
}