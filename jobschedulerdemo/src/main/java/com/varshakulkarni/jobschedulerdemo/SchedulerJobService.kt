package com.varshakulkarni.jobschedulerdemo

import android.app.job.JobParameters
import android.app.job.JobService
import com.varshakulkarni.common.DateTimeUtil
import com.varshakulkarni.common.NotificationUtil

class SchedulerJobService : JobService() {
    private var parameters: JobParameters? = null

    override fun onStartJob(parameters: JobParameters?): Boolean {
        if (parameters != null) {
            this.parameters = parameters
        }
        return if (DateTimeUtil().date.after(DateTimeUtil.Times.startTime) && DateTimeUtil().date.before(
                DateTimeUtil.Times.endTime
            )
        ) {

            NotificationUtil.createNotification(this, R.drawable.ic_launcher_background)
            true
        } else {
            false
        }
    }

    override fun onStopJob(parameters: JobParameters?): Boolean {
        return true
    }
}
