package com.ivan.m.fleetmanager.domain.model

data class VehicleLastData(
    val id: Long,
    val plate: String,
    val driverName: String? = null,
    val address: String,
    val timestamp: String,
    val speed: Long
)
