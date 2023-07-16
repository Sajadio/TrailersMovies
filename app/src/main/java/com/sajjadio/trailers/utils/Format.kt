package com.sajjadio.trailers.utils


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
    return String.format("%dh %02dm", hours, minutes)
}
