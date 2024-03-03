package com.example.simplelist

import com.example.Constants.BASE_IMAGE_URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object Util {

    fun String.makeLink(): String {
        return BASE_IMAGE_URL + this
    }

    fun String.toSimpleDateFormat(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        return try {
            val date: Date = inputFormat.parse(this)!!
            outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}