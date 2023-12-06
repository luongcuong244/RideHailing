package com.cuongnl.ridehailing.screens.waitingdriver.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.MapViewModel
import com.google.maps.android.compose.GoogleMap

@Composable
fun ColumnScope.MapView(
    mapViewModel: MapViewModel = viewModel(),
) {

    val markerSize = with(LocalDensity.current) { (Constant.MARKER_SIZE_IN_PIXEL / density).dp }

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            properties = mapViewModel.properties.value,
            uiSettings = mapViewModel.uiSettings.value,
            cameraPositionState = mapViewModel.cameraPositionState.value,
            contentPadding = PaddingValues(bottom = LocalConfiguration.current.screenHeightDp.dp)
        ) {

        }
    }
}