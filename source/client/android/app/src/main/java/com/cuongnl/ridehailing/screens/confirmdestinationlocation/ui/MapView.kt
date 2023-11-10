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
import com.cuongnl.ridehailing.viewmodel.ConfirmDestinationLocationViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    confirmDestinationLocationViewModel: ConfirmDestinationLocationViewModel = viewModel(),
    mapViewModel: MapViewModel = viewModel()
) {

    val context = LocalContext.current

    if (confirmDestinationLocationViewModel.selectedLatLng.value != null) {

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                confirmDestinationLocationViewModel.selectedLatLng.value!!,
                17f
            )
        }

        LaunchedEffect(cameraPositionState.isMoving) {
            if (!cameraPositionState.isMoving) {
                val target = cameraPositionState.position.target
                confirmDestinationLocationViewModel.setSelectedLatLngAndLoadAddress(context, target)
            }
        }

        Box(Modifier.fillMaxSize()) {

            GoogleMap(
                modifier = Modifier.matchParentSize(),
                properties = mapViewModel.properties.value,
                uiSettings = mapViewModel.uiSettings.value,
                cameraPositionState = cameraPositionState,
            )

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
}