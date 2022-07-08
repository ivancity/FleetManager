package com.ivan.m.fleetmanager.common

import android.text.format.DateUtils
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {
    fun yesterdayString(): String {
        val today = LocalDate.now()
        return today.minusDays(1).format(DateTimeFormatter.ISO_DATE);
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