package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng

class ConfirmDestinationLocationViewModel : ViewModel() {

    private val _selectedLocation = mutableStateOf<Location?>(null)
    private val _selectedAddress = mutableStateOf("")
    private val _isAddressLoading = mutableStateOf(false)

    val selectedLocation: State<Location?> = _selectedLocation
    val selectedAddress: State<String?> = _selectedAddress
    val isAddressLoading: State<Boolean> = _isAddressLoading

    fun setSelectedLocationAndLoadAddress(context: Context, location: Location) {
        _selectedLocation.value = location

        val latLng = LatLng(location.latitude, location.longitude)

        loadAddress(context, latLng)
    }

    private fun loadAddress(context: Context, latLng: LatLng) {

        setAddressLoading(true)

        MapUtils.getAddressByCoordinates(
            context,
            latLng,
            onSuccess = {
                _selectedAddress.value = it
                setAddressLoading(false)
            },
            onFailure = {
                setAddressLoading(false)
            }
        )
    }

    fun setAddressLoading(isLoading: Boolean) {
        _isAddressLoading.value = isLoading
    }
}