package com.ivan.m.fleetmanager.presentation.vehicle_history

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.m.fleetmanager.common.Constants
import com.ivan.m.fleetmanager.common.Resource
import com.ivan.m.fleetmanager.common.Utils
import com.ivan.m.fleetmanager.common.Utils.getPreviousDateFrom
import com.ivan.m.fleetmanager.domain.model.LatLong
import com.ivan.m.fleetmanager.domain.use_case.get_history.GetVehicleHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class VehicleHistoryViewModel @Inject constructor(
    private val getVehicleHistoryUseCase: GetVehicleHistoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(VehicleHistoryState())
    val state: State<VehicleHistoryState> = _state
    var objectId: String? = null

    var plateState by mutableStateOf("")

    init {
        savedStateHandle.get<String>(Constants.PARAM_ID)?.let { id ->
            savedStateHandle.get<String>(Constants.PARAM_PLATE)?.let { plate ->
                plateState = plate
                objectId = id
                val yesterday = Utils.yesterdayString()
                val today = LocalDate.now().toString()
                getVehicleHistory(id, yesterday, today)
            }
        }
    }

    fun handleDateChanged(date: String) {
        val id = objectId ?: return
        val previousDate = getPreviousDateFrom(date)
        getVehicleHistory(id, previousDate, date)
    }

    private fun getVehicleHistory(
        objectId: String,
        begTimestamp: String,
        endTimestamp: String
    ) {
        getVehicleHistoryUseCase(
            begTimestamp = begTimestamp,
            endTimestamp = endTimestamp,
            objectId = objectId
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = VehicleHistoryState(
                        latLongList = result.data ?: emptyList(),
                        firstCoordinate = result.data?.first(),
                        lastCoordinate = result.data?.last()
                    )
                }
                is Resource.Error -> {
                    _state.value = VehicleHistoryState(
                        error = result.message ?: "unexpected value"
                    )
                }
                is Resource.Loading -> {
                    _state.value = VehicleHistoryState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}