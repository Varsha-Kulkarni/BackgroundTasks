package com.varshakulkarni.common

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {
    private val now: Calendar = Calendar.getInstance()
    private val hour = now[Calendar.HOUR_OF_DAY]
    private val minute = now[Calendar.MINUTE]

    object Times {
        val startTime: Date = SimpleDateFormat("HH:mm").parse("08:00")
        val endTime: Date = SimpleDateFormat("HH:mm").parse("20:00")

    }

    val date: Date = SimpleDateFormat("HH:mm").parse("$hour:$minute")
}