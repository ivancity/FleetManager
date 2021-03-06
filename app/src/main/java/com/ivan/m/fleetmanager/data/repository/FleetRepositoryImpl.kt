package com.ivan.m.fleetmanager.data.repository

import com.ivan.m.fleetmanager.data.remote.FleetApi
import com.ivan.m.fleetmanager.data.remote.dto.toLatLong
import com.ivan.m.fleetmanager.data.remote.dto.toVehicleLastData
import com.ivan.m.fleetmanager.domain.model.LatLong
import com.ivan.m.fleetmanager.domain.model.VehicleLastData
import com.ivan.m.fleetmanager.domain.preferences.Preferences
import com.ivan.m.fleetmanager.domain.repository.FleetRepository
import javax.inject.Inject

/**
 * Repository implementation for the FleetApi interface.
 */
class FleetRepositoryImpl @Inject constructor(
    private val preferences: Preferences,
    private val api: FleetApi
) : FleetRepository {

    override suspend fun getLatestData(): List<VehicleLastData> {
        val key = preferences.loadApiKey()
        val lastResponse = api.getLastData(key = key)
        return lastResponse.response.map { it.toVehicleLastData() }
    }

    override suspend fun getVehicleHistory(
        begTimestamp: String,
        endTimestamp: String,
        objectId: String
    ): List<LatLong> {
        val key = preferences.loadApiKey()
        val rawResponse = api.getRawData(
            begTimestamp = begTimestamp,
            endTimestamp = endTimestamp,
            objectId = objectId,
            key = key
        )
        return rawResponse.response.map { it.toLatLong() }
    }
}