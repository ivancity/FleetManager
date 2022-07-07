package com.ivan.m.fleetmanager.presentation.latest_data_list

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivan.m.fleetmanager.presentation.Screen

@Composable
fun LatestDataListScreen(
    navController: NavController,
    viewModel: LatestDataViewModel = hiltViewModel()
) {
    LatestDataBody(goToHistory = {
        navController.navigate(
            Screen.VehicleHistoryScreen.route
        )
    })
}

@Composable
fun LatestDataBody(goToHistory: () -> Unit) {
    Text(text = "in LatestData Screen")
    Button(onClick = {
        goToHistory()
    }) {
        Text(text = "Simple Button")
    }
}

@Preview
@Composable
fun ComposablePreview() {
    LatestDataBody(goToHistory = {})
}