package com.ivan.m.fleetmanager.common

import android.text.format.DateUtils
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {
    fun yesterdayString(): String {
        val today = LocalDate.now()
        return subtractOneDayFrom(today)
    }

    fun metersToKm(meters: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(meters * 0.001)
    }

    fun getPreviousDateFrom(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateParsed = LocalDate.parse(date, formatter)
        return subtractOneDayFrom(dateParsed)
    }

    private fun subtractOneDayFrom(date: LocalDate): String {
        return date.minusDays(1).format(DateTimeFormatter.ISO_DATE)
    }

    fun timestampToZonedDateTime(timestamp: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")
        val zdtWithZoneOffset = ZonedDateTime
            .parse(timestamp, formatter)
        val zdtInLocalTimeline = zdtWithZoneOffset
            .withZoneSameInstant(ZoneId.systemDefault())
        val currentMilliseconds = System.currentTimeMillis()
        val timestampMilliseconds = zdtInLocalTimeline.toInstant().toEpochMilli()
        return DateUtils.getRelativeTimeSpanString(
            timestampMilliseconds,
            currentMilliseconds,
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    }
}