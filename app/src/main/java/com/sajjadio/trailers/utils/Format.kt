package com.sajjadio.trailers.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

fun formatVoteCount(value: Int): String {
    if (value < 1000) return value.toString()
    val exp = ln(value.toDouble()).div(ln(1000.0)).toInt()
    return String.format("%.2f %c", value.div(1000.0.pow(exp)), "kMGTPE"[exp - 1])
}

fun formatHourMinutes(value: Int): String {
    val hours = value.div(60)
    val minutes = value % 60
    return String.format("%d:%02d", hours, minutes) + " m"
}

object DateTimeFormatter {
    private const val DATE_FORMAT = "yyyy-MM-dd"
    private const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

    fun toCalendarOrNull(format: String = DATE_FORMAT, value: String): Calendar? {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        val date = try {
            dateFormat.parse(value)
        } catch (e: Exception) {
            null
        }
        return date?.let { Calendar.getInstance().apply { time = it } }
    }

    fun format(format: String = DATE_FORMAT, calendar: Calendar): String? {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}