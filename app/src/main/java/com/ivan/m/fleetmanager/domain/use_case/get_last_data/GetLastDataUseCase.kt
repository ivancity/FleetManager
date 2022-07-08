package com.ivan.m.fleetmanager.domain.use_case.get_last_data

import com.ivan.m.fleetmanager.common.Resource
import com.ivan.m.fleetmanager.domain.model.VehicleLastData
import com.ivan.m.fleetmanager.domain.repository.FleetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLatestDataUseCase @Inject constructor(
    private val repository: FleetRepository
) {

    /**
     * We are overriding the operator function in this way getCoinUseCase can be used as a function.
     */
    operator fun invoke(): Flow<Resource<List<VehicleLastData>>> = flow {
        try {
            emit(Resource.Loading())
            val latestData = repository.getLatestData()
            emit(Resource.Success(latestData))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server."))
        }
    }
}