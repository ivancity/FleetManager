package com.ivan.m.fleetmanager.presentation.vehicle_history

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.m.fleetmanager.common.Constants
import com.ivan.m.fleetmanager.common.Resource
import com.ivan.m.fleetmanager.domain.use_case.get_history.GetVehicleHistoryUseCase
import com.ivan.m.fleetmanager.presentation.latest_data_list.LatestDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VehicleHistoryViewModel @Inject constructor(
    private val getVehicleHistoryUseCase: GetVehicleHistoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(VehicleHistoryState())
    val state: State<VehicleHistoryState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_HISTORY)?.let {
            getVehicleHistory(it, "2022-07-07", "2022-07-08")
        }
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
                        latLongList = result.data ?: emptyList()
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