package com.sulistyolabs.footballschedule.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE

}

fun View.invisible() {
    visibility = View.INVISIBLE

}

fun changeTimezone(time: String?): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.US)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date: Date = dateFormat.parse(time)

    val tz: TimeZone = TimeZone.getTimeZone("Asia/Jakarta")
    val newDateFormat = SimpleDateFormat("HH:mm", Locale.US)
    newDateFormat.timeZone = tz

    return newDateFormat.format(date)
}

fun getMilliseconds(date: String, isStart: Boolean): Long {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd, HH:mm", Locale.US)
        val date1 = sdf.parse(date)
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val beginCal = Calendar.getInstance()

        val minutes: Int
        minutes = if (isStart) cal1.get(Calendar.MINUTE)
        else cal1.get(Calendar.MINUTE) + 90

        beginCal.set(
                cal1.get(Calendar.YEAR),
                cal1.get(Calendar.MONTH),
                cal1.get(Calendar.DAY_OF_MONTH),
                cal1.get(Calendar.HOUR_OF_DAY),
                minutes
        )
        beginCal.timeInMillis
    } catch (e: Exception) {
        Date().time
    }
}

fun isConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else false
}
