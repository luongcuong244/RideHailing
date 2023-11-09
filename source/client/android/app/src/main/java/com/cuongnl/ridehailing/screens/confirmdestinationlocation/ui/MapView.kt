package com.cuongnl.ridehailing.screens.confirmdestinationlocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.bitmapDescriptorFromVector
import com.cuongnl.ridehailing.screens.confirmdestinationlocation.ConfirmDestinationLocation
import com.cuongnl.ridehailing.viewmodel.ConfirmDestinationLocationViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdate
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    confirmDestinationLocationViewModel: ConfirmDestinationLocationViewModel = viewModel(),
    mapViewModel: MapViewModel = viewModel()
) {

    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(confirmDestinationLocationViewModel.selectedLocation.value) {
        if (confirmDestinationLocationViewModel.selectedLocation.value != null) {

        }
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
//            singapore.value = cameraPositionState.position.target
//            ConfirmDestinationLocation.getPlaceIdByCoordinates(context, singapore.value)
        }
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = mapViewModel.properties.value,
            uiSettings = mapViewModel.uiSettings.value,
            cameraPositionState = cameraPositionState,
        ) {

            confirmDestinationLocationViewModel.selectedLocation.value?.let {

            }
            if (confirmDestinationLocationViewModel.selectedLocation.value != null) {

                val latLag = confirmDestinationLocationViewModel.selectedLocation.value?.latitude?.let {
                    confirmDestinationLocationViewModel.selectedLocation.value?.longitude?.let { it1 ->
                        com.google.android.gms.maps.model.LatLng(
                            it,
                            it1
                        )
                    }
                }

                Marker(
                    state = MarkerState(position = latLag),
                    icon = context.bitmapDescriptorFromVector(R.drawable.icons_dropoffmarker),
                )
            }
        }

        val pxValue = with(LocalDensity.current) { (150 / density).dp }

        Image(
            painter = painterResource(id = R.drawable.icons_dropoffmarker),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = pxValue)
                .align(Alignment.Center)
                .size(pxValue)
        )
    }
}