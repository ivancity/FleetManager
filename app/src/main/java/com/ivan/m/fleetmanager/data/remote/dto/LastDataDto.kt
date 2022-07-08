package com.ivan.m.fleetmanager.data.remote.dto

import android.text.format.DateUtils.MINUTE_IN_MILLIS
import android.text.format.DateUtils.getRelativeTimeSpanString
import com.ivan.m.fleetmanager.domain.model.VehicleLastData
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class LastDataDto (
    val status: Long,
    val response: List<LastResponse>
)

data class LastResponse (
    val objectID: Long,
    val orgID: Long,
    val timestamp: String,
    val latitude: Double,
    val longitude: Double,
    val speed: Long,
    val enginestate: Long,
    val gpsstate: Boolean,
    val direction: Long? = null,
    val fuel: Any? = null,
    val power: Double,
    val canDistance: Double? = null,
    val available: Any? = null,
    val driverID: Long? = null,
    val driverUUID: Any? = null,
    val driverName: String? = null,
    val driverKey: Any? = null,
    val driverPhone: Any? = null,
    val driverStatuses: List<Any?>? = null,
    val driverIsOnDuty: Boolean? = null,
    val dutyTags: List<Any?>? = null,
    val pairedObjectID: Any? = null,
    val pairedObjectName: Any? = null,
    val lastEngineOnTime: String,
    val inPrivateZone: Boolean,
    val offWorkSchedule: Boolean? = null,
    val tripPurposeDINSet: Any? = null,
    val tcoData: Any? = null,
    val tcoCardIsPresent: Boolean,
    val address: String,
    val addressArea: Boolean,
    val addressAreaID: Any? = null,
    val addressAreaUUID: Any? = null,
    val displayColor: Any? = null,
    val employeeID: Any? = null,
    val currentOdometer: Any? = null,
    val currentWorkhours: Any? = null,
    val enforcePrivacyFilter: Any? = null,
    val evStateOfCharge: Any? = null,
    val evDistanceRemaining: Any? = null,
    val customValues: List<Any?>,
    val eventType: String,
    val objectName: String,
    val externalID: Any? = null,
    val plate: String,
    val canrpm: Long? = null,
    val greenDrivingValue: Double? = null,
    val greenDrivingType: Long? = null
)

fun LastResponse.toVehicleLastData(): VehicleLastData {
    val timeAgoFormat = timestampToZonedDateTime(timestamp)
    return VehicleLastData(
        id = orgID,
        plate = plate,
        driverName = driverName,
        address = address,
        timestamp = timeAgoFormat,
        speed = speed
    )
}

fun timestampToZonedDateTime(timestamp: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")
    val zdtWithZoneOffset = ZonedDateTime
        .parse(timestamp, formatter)
    val zdtInLocalTimeline = zdtWithZoneOffset
        .withZoneSameInstant(ZoneId.systemDefault())
    val currentMilliseconds = System.currentTimeMillis()
    val timestampMilliseconds = zdtInLocalTimeline.toInstant().toEpochMilli()
    return getRelativeTimeSpanString(
        timestampMilliseconds,
        currentMilliseconds,
        MINUTE_IN_MILLIS
    ).toString()
}