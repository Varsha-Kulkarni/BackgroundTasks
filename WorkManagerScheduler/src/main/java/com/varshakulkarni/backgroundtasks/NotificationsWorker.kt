package com.varshakulkarni.backgroundtasks

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.varshakulkarni.common.NotificationUtil

class NotificationsWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        NotificationUtil.createNotification(applicationContext, R.drawable.ic_launcher_background)

        return Result.success()
    }
}