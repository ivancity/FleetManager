package com.ivan.m.fleetmanager.presentation.latest_data_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivan.m.fleetmanager.domain.model.VehicleLastData
import com.ivan.m.fleetmanager.presentation.components.AppBarState
import com.ivan.m.fleetmanager.presentation.latest_data_list.components.CustomDialog
import com.ivan.m.fleetmanager.presentation.latest_data_list.components.LatestDataItem

@Composable
fun LatestDataListScreen(
    onComposing: (AppBarState) -> Unit,
    goToVehicleHistoryScreen: (objectId: String, plate: String) -> Unit,
    viewModel: LatestDataViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "Vehicles",
                actions = {
                    IconButton(onClick = {
                        viewModel.getLatestData()
                    }) {
                        Icon(Icons.Filled.Refresh, "refreshIcon")
                    }
                    IconButton(onClick = {
                        showDialog.value = true
                    }) {
                        Icon(Icons.Filled.Key, "keyIcon")
                    }
                }
            )
        )
    }

    if (showDialog.value) {
        CustomDialog(
            value = viewModel.getApiKey(),
            setShowDialog = {
                showDialog.value = it
            }, setValue = {
                viewModel.setApiKey(apiKey = it)
            }
        )
    }

    LatestDataBody(
        state = state,
        goToHistory = { id, plate ->
            goToVehicleHistoryScreen(id, plate)
        }
    )
}

@Composable
fun LatestDataBody(
    state: LatestDataState,
    goToHistory: (objectId: String, plate: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (!state.apiKeyIsFound) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text ="API key was not found please add one. You can try tapping on the key icon to enter a key.",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }
        if (state.error.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Something went wrong")
            }
        }
        if (state.latestData.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.latestData) { data ->
                    LatestDataItem(
                        id = data.id.toString(),
                        plate = data.plate,
                        name = data.driverName ?: "",
                        address = data.address,
                        speed = data.speed.toString(),
                        timestamp = data.timestamp,
                        onClick = goToHistory
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun ComposablePreview() {
    LatestDataBody(
        state = LatestDataState(
            latestData = listOf(
                VehicleLastData(
                    id = 654,
                    plate = "XYZ987",
                    driverName = "Driver Name Some very long name",
                    address = "Address Long here that is actually ver long more than 1 line.",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 655,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 0,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 656,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 657,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 658,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 0,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 659,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 660,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 661,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 662,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                ),
                VehicleLastData(
                    id = 663,
                    plate = "XYZ987",
                    driverName = "Driver Name",
                    address = "Address Long here",
                    speed = 20,
                    timestamp = "1h 3m ago"
                )
            ),
            isLoading = false,
            error = ""
        ),
        goToHistory = {_, _ ->}
    )
}