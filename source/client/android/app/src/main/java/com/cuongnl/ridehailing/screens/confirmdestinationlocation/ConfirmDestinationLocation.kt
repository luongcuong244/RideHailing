package com.cuongnl.ridehailing.screens.confirmdestinationlocation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class ConfirmDestinationLocation : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val uiSettings by remember {
                mutableStateOf(MapUiSettings().copy(zoomControlsEnabled = false))
            }
            val properties by remember {
                mutableStateOf(MapProperties(mapType = MapType.NORMAL))
            }

            val singapore = LatLng(21.056141, 105.576163)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(singapore, 17f)
            }

            Box(Modifier.fillMaxSize()) {
                GoogleMap(
                    modifier = Modifier.matchParentSize(),
                    properties = properties,
                    uiSettings = uiSettings,
                    cameraPositionState = cameraPositionState,
                ) {
                    Marker(
                        state = MarkerState(position = singapore),
                        title = "Singapore",
                        snippet = "Marker in Singapore",
                    )
                }
            }
        }
    }
}