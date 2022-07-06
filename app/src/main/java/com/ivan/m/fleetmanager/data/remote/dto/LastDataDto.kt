package com.ivan.m.fleetmanager.data.remote.dto

import com.ivan.m.fleetmanager.domain.model.VehicleLastData

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
    return VehicleLastData(
        id = orgID,
        plate = plate,
        driverName = driverName,
        address = address,
        timestamp = timestamp,
        speed = speed
    )
}