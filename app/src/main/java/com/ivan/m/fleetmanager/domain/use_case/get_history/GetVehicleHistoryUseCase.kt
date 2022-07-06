package com.ivan.m.fleetmanager.domain.use_case.get_history

import com.ivan.m.fleetmanager.common.Resource
import com.ivan.m.fleetmanager.domain.model.LatLong
import com.ivan.m.fleetmanager.domain.repository.FleetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVehicleHistoryUseCase @Inject constructor(
    private val repository: FleetRepository
) {
    operator fun invoke(
        begTimestamp: String,
        endTimestamp: String,
        objectId: String
    ): Flow<Resource<List<LatLong>>> = flow {
        try {
            emit(Resource.Loading())
            val historyData = repository.getVehicleHistory(
                begTimestamp = begTimestamp,
                endTimestamp = endTimestamp,
                objectId = objectId
            )
            emit(Resource.Success(historyData))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server."))
        }
    }

}