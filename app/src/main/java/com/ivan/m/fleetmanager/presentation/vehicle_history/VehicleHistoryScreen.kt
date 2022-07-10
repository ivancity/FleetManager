package com.ivan.m.fleetmanager.presentation.vehicle_history

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.SphericalUtil
import com.google.maps.android.compose.*
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
    val googleCoordinates = state.latLongList.map { LatLng(it.latitude, it.longitude) }
//    val length = SphericalUtil.computeLength(googleCoordinates)


    Box(Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.error.isNotEmpty()) {
            Text(
                text = "Something went wrong",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (centerMarker != null) {
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(centerMarker, 11.5f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Polyline(
                    points = googleCoordinates
                )
                val startMarker = LatLng(state.firstCoordinate.latitude, state.firstCoordinate.longitude)
                Marker(
                    state = MarkerState(
                        position = startMarker
                    ),
                    title = "Start"
                )
                if (state.lastCoordinate != null) {
                    val endMarker = LatLng(state.lastCoordinate.latitude, state.lastCoordinate.longitude)
                    Marker(
                        state = MarkerState(
                            position = endMarker
                        ),
                        title = "End"
                    )
                    val bounds = LatLngBounds.Builder()
                    bounds.include(startMarker)
                    bounds.include(endMarker)
                    cameraPositionState.move(CameraUpdateFactory.newLatLngBounds(bounds.build(), 200))
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
        state = VehicleHistoryState(
            isLoading = true
        )
    ) { _ -> }
}