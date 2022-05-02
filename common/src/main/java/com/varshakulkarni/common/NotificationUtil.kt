package com.varshakulkarni.common

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import com.varshakulkarni.common.Constants.Notifications.CHANNEL_ID
import com.varshakulkarni.common.Constants.Notifications.CHANNEL_NAME
import com.varshakulkarni.common.Constants.Notifications.NOTIFICATION_ID

object NotificationUtil {
    fun createNotification(context: Context, resourceId: Int) {
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
        } else {
            notificationBuilder = Notification.Builder(context)
                .setPriority(Notification.PRIORITY_MAX)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        }
        val notification = notificationBuilder?.setContentTitle("New Notification")
            ?.setAutoCancel(true)
            ?.setSmallIcon(resourceId)
            ?.setContentText("New Notification Alert! ${DateTimeUtil().date}")?.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}