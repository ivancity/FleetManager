package com.ivan.m.fleetmanager.presentation.latest_data_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LatestDataListScreen(
    navController: NavController,
    viewModel: LatestDataViewModel = hiltViewModel()
) {
    Text(
        text = "in LatestData Screen"
    )
}