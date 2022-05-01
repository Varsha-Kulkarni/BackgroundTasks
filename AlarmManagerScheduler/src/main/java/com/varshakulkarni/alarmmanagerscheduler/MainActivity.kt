package com.varshakulkarni.alarmmanagerscheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val now = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
        }
        val alarmManager = this.getSystemService(
            Context.ALARM_SERVICE
        ) as AlarmManager

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            Constants.Alarm.ALARM_ID,
            Intent(this, SchedulerBroadcastReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            now.timeInMillis,
            pendingIntent
        )
    }
}