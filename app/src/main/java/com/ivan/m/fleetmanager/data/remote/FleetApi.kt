package com.ivan.m.fleetmanager.data.remote

import com.ivan.m.fleetmanager.data.remote.dto.LastDataDto
import com.ivan.m.fleetmanager.data.remote.dto.RawDataDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is the API that we use to connect to the REST API services.
 */
interface FleetApi {
    @GET("seeme/Api/Vehicles/getLastData")
    suspend fun getLastData(
        @Query("key") key: String,
        @Query("json") json: String = "true"
    ): LastDataDto

    @GET("seeme/Api/Vehicles/getRawData")
    suspend fun getRawData(
        @Query("begTimestamp") begTimestamp: String,
        @Query("endTimestamp") endTimestamp: String,
        @Query("objectId") objectId: String,
        @Query("key") key: String,
        @Query("json") json: String = "true"
    ): RawDataDto
}