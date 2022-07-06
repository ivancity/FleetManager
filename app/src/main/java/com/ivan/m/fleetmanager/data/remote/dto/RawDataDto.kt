package com.ivan.m.fleetmanager.data.remote.dto

import com.ivan.m.fleetmanager.domain.model.LatLong

data class RawDataDto (
    val status: Long,
    val response: List<RawResponse>
)

data class RawResponse (
    val timestamp: String,
    val speed: Long,
    val direction: Long,
    val latitude: Double,
    val gpsState: Boolean,
    val longitude: Double,
    val engineStatus: Boolean,
    val power: Double,
    val satellites: Long,
    val deltaDistance: Long,
    val driverID: Long,
    val eventTypeDEC: String? = null
)

fun RawResponse.toLatLong(): LatLong {
    return LatLong(
        latitude = latitude,
        longitude = longitude
    )
}
