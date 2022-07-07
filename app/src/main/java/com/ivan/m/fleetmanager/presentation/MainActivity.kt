package com.ivan.m.fleetmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivan.m.fleetmanager.R
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
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = getString(R.string.vehicles)
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White,
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Filled.Refresh, "backIcon")
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Filled.Key, "keyIcon")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LatestDataListScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(
                            route = Screen.LatestDataListScreen.route
                        ) {
                            LatestDataListScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.VehicleHistoryScreen.route
                        ) {
                            VehicleHistoryScreen()
                        }
                    }
                }
            }
        }
    }
}