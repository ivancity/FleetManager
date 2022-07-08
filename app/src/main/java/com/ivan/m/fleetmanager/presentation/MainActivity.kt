package com.ivan.m.fleetmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                        val showBackButton by derivedStateOf {
                            navController.previousBackStackEntry != null
                        }
                        TopAppBar(
                            title = {
                                Text(
                                    text = appBarState.title
                                )
                            },
                            navigationIcon = if (showBackButton) {
                                {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            } else {
                                null
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White,
                            actions = {
                                appBarState.actions?.invoke(this)
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
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.VehicleHistoryScreen.route
                        ) {
                            VehicleHistoryScreen(
                                onComposing = {
                                    appBarState = it
                                },
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}