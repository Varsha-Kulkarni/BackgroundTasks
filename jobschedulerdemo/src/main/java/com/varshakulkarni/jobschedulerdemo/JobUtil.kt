package com.varshakulkarni.jobschedulerdemo

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import com.varshakulkarni.jobschedulerdemo.Constants.Jobs.JOB_DEADLINE

object JobUtil {
    fun scheduleJob(context: Context) {
        val jobInfo = JobInfo.Builder(
            Constants.Jobs.JOB_ID,
            ComponentName(context, SchedulerJobService::class.java)
        )

        val job = jobInfo.setRequiresCharging(false)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPeriodic(JOB_DEADLINE.toLong())
            .build()

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancelAll()
        jobScheduler.schedule(job)
    }
}