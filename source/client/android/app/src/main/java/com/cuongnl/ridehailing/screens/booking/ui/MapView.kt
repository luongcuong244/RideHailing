package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.bitmapDescriptorFromVector
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    mapViewModel: MapViewModel = viewModel(),
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            bookingActivityUiViewModel.destinationLocationLatLng.value,
            15f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = mapViewModel.properties.value,
        uiSettings = mapViewModel.uiSettings.value,
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            icon = context.bitmapDescriptorFromVector(R.drawable.icons_pickupmarker, Constant.MARKER_SIZE_IN_PIXEL),
            state = MarkerState(position = bookingActivityUiViewModel.pickupLocationLatLng.value)
        )
        Marker(
            icon = context.bitmapDescriptorFromVector(R.drawable.icons_dropoffmarker, Constant.MARKER_SIZE_IN_PIXEL),
            state = MarkerState(position = bookingActivityUiViewModel.destinationLocationLatLng.value)
        )

        Polyline(
            points = bookingActivityUiViewModel.points,
            color = colorResource(id = R.color.blue),
            width = 15f,
            jointType = JointType.ROUND,
        )
    }

}