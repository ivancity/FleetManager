package com.ivan.m.fleetmanager.presentation.latest_data_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.m.fleetmanager.common.Resource
import com.ivan.m.fleetmanager.domain.preferences.Preferences
import com.ivan.m.fleetmanager.domain.use_case.get_last_data.GetLatestDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LatestDataViewModel @Inject constructor(
    private val preferences: Preferences,
    private val getLatestDataUseCase: GetLatestDataUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LatestDataState())
    val state: State<LatestDataState> = _state

    init {
        getLatestData()
    }

    fun getLatestData() {
        val apiKey = preferences.loadApiKey()
        if (apiKey.isEmpty()) {
            _state.value = LatestDataState(
                apiKeyIsFound = false
            )
            return
        }

        getLatestDataUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = LatestDataState(
                        latestData = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = LatestDataState(
                        error = result.message ?: "unexpected value"
                    )
                }
                is Resource.Loading -> {
                    _state.value = LatestDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}