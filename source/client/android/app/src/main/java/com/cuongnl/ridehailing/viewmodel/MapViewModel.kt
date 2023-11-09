package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings

class MapViewModel : ViewModel() {

    private val _uiSettings = mutableStateOf(MapUiSettings().copy(zoomControlsEnabled = false))
    private val _properties = mutableStateOf(MapProperties(mapType = MapType.NORMAL))

    val uiSettings: State<MapUiSettings> = _uiSettings
    val properties: State<MapProperties> = _properties

    fun setUiSettings(uiSettings: MapUiSettings) {
        _uiSettings.value = uiSettings
    }

    fun setProperties(properties: MapProperties) {
        _properties.value = properties
    }
}