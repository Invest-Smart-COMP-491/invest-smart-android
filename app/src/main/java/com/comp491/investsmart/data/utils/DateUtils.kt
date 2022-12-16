package com.comp491.investsmart.data.utils

import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDateString(dateString: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = format.parse(dateString)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date?.time ?: System.currentTimeMillis()

    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)

    return "$dayOfMonth. $month $year"
}
