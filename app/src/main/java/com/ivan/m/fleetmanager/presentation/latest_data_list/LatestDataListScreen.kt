package com.ivan.m.fleetmanager.presentation.latest_data_list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivan.m.fleetmanager.R
import com.ivan.m.fleetmanager.domain.model.VehicleLastData
import com.ivan.m.fleetmanager.presentation.Screen
import com.ivan.m.fleetmanager.presentation.components.AppBarState
import com.ivan.m.fleetmanager.presentation.latest_data_list.components.LatestDataItem

@Composable
fun LatestDataListScreen(
    onComposing: (AppBarState) -> Unit,
    goToVehicleHistoryScreen: () -> Unit,
    viewModel: LatestDataViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "Vehicles",
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "LatestDataListScreen: Reresh")
                    }) {
                        Icon(Icons.Filled.Refresh, "backIcon")
                    }
                    IconButton(onClick = {
                        Log.d("TAG", "LatestDataListScreen: Key")
                    }) {
                        Icon(Icons.Filled.Key, "keyIcon")
                    }
                }
            )
        )
    }
    LatestDataBody(
        latestData = state.latestData,
        goToHistory = {
            goToVehicleHistoryScreen()
        }
    )
}

@Composable
fun LatestDataBody(
    latestData: List<VehicleLastData>,
    goToHistory: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(latestData) { data ->
                LatestDataItem(
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

@Preview
@Composable
fun ComposablePreview() {
    LatestDataBody(
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
                speed = 20,
                timestamp = "1h 3m ago"
            )
        ),
        goToHistory = {}
    )
}