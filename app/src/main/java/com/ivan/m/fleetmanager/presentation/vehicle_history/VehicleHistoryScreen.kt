package com.ivan.m.fleetmanager.presentation.vehicle_history

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivan.m.fleetmanager.presentation.components.AppBarState

@Composable
fun VehicleHistoryScreen(
    onComposing: (AppBarState) -> Unit,
    viewModel: VehicleHistoryViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "Location History",
                showBackButton = true,
                actions = null
            )
        )
    }
    Text(text = "Vehicle History")
}