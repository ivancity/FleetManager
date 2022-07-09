package com.ivan.m.fleetmanager.presentation.vehicle_history

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ivan.m.fleetmanager.domain.model.LatLong
import com.ivan.m.fleetmanager.presentation.components.AppBarState
import com.ivan.m.fleetmanager.presentation.vehicle_history.components.DatePickerComponent

@Composable
fun VehicleHistoryScreen(
    onComposing: (AppBarState) -> Unit,
    viewModel: VehicleHistoryViewModel = hiltViewModel()
) {
    val plate = viewModel.plateState
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        onComposing(
            AppBarState(
                title = "Location History: $plate",
                showBackButton = true,
                actions = null
            )
        )
    }
    VehicleHistoryBody(
        state = state
    ) { dateString ->
        viewModel.handleDateChanged(dateString)
    }
}

@Composable
fun VehicleHistoryBody(
    state: VehicleHistoryState,
    onDateChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DatePickerComponent(LocalContext.current, onDateChanged)
        MapView(state = state)
    }
}

@Composable
fun MapView(state: VehicleHistoryState) {
    val centerMarker = state.firstCoordinate?.let {  LatLng(it.latitude, it.longitude) }
    if (centerMarker != null) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(centerMarker, 11.5f)
        }
        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Polyline(points = state.latLongList.map { LatLng(it.latitude, it.longitude) })
                Marker(
                    state = MarkerState(
                        position = LatLng(state.firstCoordinate.latitude, state.firstCoordinate.longitude)
                    ),
                    title = "Start"
                )
                if (state.lastCoordinate != null) {
                    Marker(
                        state = MarkerState(
                            position = LatLng(state.lastCoordinate.latitude, state.lastCoordinate.longitude)
                        ),
                        title = "End"
                    )
                }
            }
            Text(
                text = "Trip Distance: ",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun VehicleHistoryScreenPreview() {
    VehicleHistoryBody(
        state = VehicleHistoryState()
    ) { _ -> }
}