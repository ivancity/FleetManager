package com.ivan.m.fleetmanager.presentation.vehicle_history

import com.ivan.m.fleetmanager.domain.model.LatLong

data class VehicleHistoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val latLongList: List<LatLong> = emptyList(),
    val tripDistance: String = ""
)
