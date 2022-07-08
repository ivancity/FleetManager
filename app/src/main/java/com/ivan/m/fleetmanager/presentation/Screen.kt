package com.ivan.m.fleetmanager.presentation

sealed class Screen(val route: String) {
    object LatestDataListScreen: Screen("latest_data_list_screen")
    object VehicleHistoryScreen: Screen("vehicle_history_screen")
}