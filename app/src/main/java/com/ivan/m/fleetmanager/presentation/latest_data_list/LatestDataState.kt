package com.ivan.m.fleetmanager.presentation.latest_data_list

import com.ivan.m.fleetmanager.domain.model.VehicleLastData

data class LatestDataState(
    val isLoading: Boolean = false,
    val latestData: List<VehicleLastData> = emptyList(),
    val error: String = ""
)
