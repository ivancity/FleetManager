package com.ivan.m.fleetmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivan.m.fleetmanager.common.Constants
import com.ivan.m.fleetmanager.presentation.components.AppBar
import com.ivan.m.fleetmanager.presentation.components.AppBarState
import com.ivan.m.fleetmanager.presentation.latest_data_list.LatestDataListScreen
import com.ivan.m.fleetmanager.presentation.ui.theme.FleetManagerTheme
import com.ivan.m.fleetmanager.presentation.vehicle_history.VehicleHistoryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetManagerTheme {
                val navController = rememberNavController()
                var appBarState by remember {
                    mutableStateOf(AppBarState())
                }
                Scaffold(
                    topBar = {
                        AppBar(
                            appBarState = appBarState,
                            onNavigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LatestDataListScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(
                            route = Screen.LatestDataListScreen.route
                        ) {
                            LatestDataListScreen(
                                onComposing = {
                                    appBarState = it
                                },
                                goToVehicleHistoryScreen = { id, plate ->
                                    navController.navigate(
                                        Screen.VehicleHistoryScreen.route + "/$id/$plate"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Screen.VehicleHistoryScreen.route + "/{${Constants.PARAM_ID}}/{${Constants.PARAM_PLATE}}"
                        ) {
                            VehicleHistoryScreen(
                                onComposing = {
                                    appBarState = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}