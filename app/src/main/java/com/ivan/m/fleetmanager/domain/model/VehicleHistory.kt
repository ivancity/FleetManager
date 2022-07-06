package com.ivan.m.fleetmanager.domain.model

data class VehicleHistory(
    val id: Long,
    val plate: String,
    val beginTimestamp: String,
    val endTimestamp: String,
    val tripDistance: String,
    val latLongList: List<LatLong>
)
