package com.ivan.m.fleetmanager.presentation.vehicle_history

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ivan.m.fleetmanager.presentation.components.AppBarState

@Composable
fun VehicleHistoryScreen(
    onComposing: (AppBarState) -> Unit,
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