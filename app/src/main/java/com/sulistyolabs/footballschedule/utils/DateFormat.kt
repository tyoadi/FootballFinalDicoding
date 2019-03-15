package com.sulistyolabs.footballschedule.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {

    fun formatDate(date: Date): String {
        return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(date)
    }

    fun formatDateCal(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }


}