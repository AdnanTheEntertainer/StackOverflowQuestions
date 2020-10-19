package com.callsigntask.queries.utils

import java.text.SimpleDateFormat
import java.util.*

object QueryDateUtils {
    fun getQueryDate(isPreviousMoth: Boolean = false): String {

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = Date().time

        if (isPreviousMoth) {
            calendar.add(Calendar.MONTH, -1)
        }
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        return formatter.format(calendar.timeInMillis)
    }
}