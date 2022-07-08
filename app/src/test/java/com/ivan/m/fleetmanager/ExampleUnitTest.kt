package com.ivan.m.fleetmanager

import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Test for getting local date time with the correct time zone.
 */
class ExampleUnitTest {
    @Test
    fun dateTimeWithLocalZone() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")
        val zdtWithZoneOffset = ZonedDateTime
            .parse("2022-07-08 18:06:04+0300", formatter)
        val zdtInLocalTimeline = zdtWithZoneOffset
            .withZoneSameInstant(ZoneId.systemDefault())
        assert(zdtWithZoneOffset.toString() == "2022-07-08T18:06:04+03:00")
    }
}