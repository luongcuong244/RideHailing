package com.cuongnl.ridehailing.screens.addingaddress.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.AddingAddressUiViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraMoveStartedReason
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    mapViewModel: MapViewModel = viewModel(),
    addingAddressUiViewModel: AddingAddressUiViewModel = viewModel()
) {
    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            addingAddressUiViewModel.selectedLatLng.value,
            17f
        )
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving && cameraPositionState.cameraMoveStartedReason == CameraMoveStartedReason.GESTURE) {
            val target = cameraPositionState.position.target

            addingAddressUiViewModel.setSelectedLatLngAndLoadAddress(
                context,
                target
            )
        }
    }

    val markerSize = with(LocalDensity.current) { (Constant.MARKER_SIZE_IN_PIXEL / density).dp }
    val markerImageInCenter = painterResource(id = R.drawable.ic_map_ic_pick)

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = mapViewModel.properties.value,
            uiSettings = mapViewModel.uiSettings.value,
            cameraPositionState = cameraPositionState,
            contentPadding = PaddingValues(bottom = LocalConfiguration.current.screenHeightDp.dp)
        )

        Image(
            painter = markerImageInCenter,
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = markerSize)
                .align(Alignment.Center)
                .size(markerSize)
        )
    }
}