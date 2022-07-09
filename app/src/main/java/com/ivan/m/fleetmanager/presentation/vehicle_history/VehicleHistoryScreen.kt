package com.ivan.m.fleetmanager.presentation.vehicle_history

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivan.m.fleetmanager.presentation.components.AppBarState
import com.ivan.m.fleetmanager.presentation.vehicle_history.components.DatePickerComponent

@Composable
fun VehicleHistoryScreen(
    onComposing: (AppBarState) -> Unit,
    viewModel: VehicleHistoryViewModel = hiltViewModel()
) {
    val plate = viewModel.plateState
    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "Location History: $plate",
                showBackButton = true,
                actions = null
            )
        )
    }
    VehicleHistoryBody { dateString ->
        viewModel.handleDateChanged(dateString)
    }
}

@Composable
fun VehicleHistoryBody(onDateChanged: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DatePickerComponent(LocalContext.current, onDateChanged)
    }
}

@Preview
@Composable
fun VehicleHistoryScreenPreview() {
    VehicleHistoryBody { _ -> }
}