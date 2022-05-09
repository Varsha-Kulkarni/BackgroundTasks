package com.varshakulkarni.backgroundtasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.varshakulkarni.backgroundtasks.Constants.Worker.WORKER_NAME
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val workManager = WorkManager.getInstance(this)
        workManager.cancelAllWork()
        val periodicRequest =
            PeriodicWorkRequest.Builder(NotificationsWorker::class.java, 1, TimeUnit.HOURS).build()
        workManager.enqueueUniquePeriodicWork(
            WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }
}