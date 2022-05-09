package com.varshakulkarni.analyzedozemode

import android.content.IntentFilter
import android.os.Bundle
import android.os.PowerManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var dozeBroadcastReceiver: DozeBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dozeBroadcastReceiver = DozeBroadcastReceiver()
        this.registerReceiver(
            dozeBroadcastReceiver,
            IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(dozeBroadcastReceiver)
    }
}