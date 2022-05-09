package com.varshakulkarni.analyzedozemode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.Log
import com.varshakulkarni.common.DateTimeUtil

class DozeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val powerManager: PowerManager =
            context.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (powerManager.isDeviceIdleMode) {
            Log.d(TAG, "device entered doze mode ${DateTimeUtil().date}")
        } else {
            Log.d(TAG, "device is active")
        }
    }

    companion object {
        const val TAG = "DozeBroadcastReceiver"
    }
}