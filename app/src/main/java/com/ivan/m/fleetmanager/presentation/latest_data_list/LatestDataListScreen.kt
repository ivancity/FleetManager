package com.ivan.m.fleetmanager.presentation.latest_data_list

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivan.m.fleetmanager.R
import com.ivan.m.fleetmanager.presentation.Screen
import com.ivan.m.fleetmanager.presentation.components.AppBarState

@Composable
fun LatestDataListScreen(
    onComposing: (AppBarState) -> Unit,
    goToVehicleHistoryScreen: () -> Unit,
    viewModel: LatestDataViewModel = hiltViewModel()
) {
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
    LatestDataBody(goToHistory = {
        goToVehicleHistoryScreen()
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