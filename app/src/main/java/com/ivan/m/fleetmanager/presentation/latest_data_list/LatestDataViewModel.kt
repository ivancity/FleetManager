package com.ivan.m.fleetmanager.presentation.latest_data_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ivan.m.fleetmanager.domain.use_case.get_last_data.GetLatestDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LatestDataViewModel @Inject constructor(
    private val getLatestDataUseCase: GetLatestDataUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LatestDataState())
    val state: State<LatestDataState> = _state

    init {
        getLatestData()
    }

    private fun getLatestData() {
        Log.d("ViewModel", "getLatestData: YESHERE")
    }

}