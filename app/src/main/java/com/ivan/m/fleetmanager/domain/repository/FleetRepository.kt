package com.ivan.m.fleetmanager.domain.repository

import com.ivan.m.fleetmanager.domain.model.LatLong
import com.ivan.m.fleetmanager.domain.model.VehicleLastData

interface FleetRepository {
    /**
     * Gets the latest data from some vehicles.
     */
    suspend fun getLatestData(): List<VehicleLastData>

    /**
     * Gets the vehicle history using some objectId as a parameter to query.
     */
    suspend fun getVehicleHistory(
        begTimestamp: String,
        endTimestamp: String,
        objectId: String,
    ): List<LatLong>
}