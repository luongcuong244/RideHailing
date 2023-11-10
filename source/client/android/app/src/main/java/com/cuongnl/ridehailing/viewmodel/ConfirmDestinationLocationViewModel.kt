package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng

class ConfirmDestinationLocationViewModel : ViewModel() {

    private val _selectedLatLng = mutableStateOf<LatLng?>(null)
    private val _selectedAddress = mutableStateOf("")
    private val _isAddressLoading = mutableStateOf(false)

    val selectedLatLng: State<LatLng?> = _selectedLatLng
    val selectedAddress: State<String?> = _selectedAddress
    val isAddressLoading: State<Boolean> = _isAddressLoading

    fun setSelectedLatLngAndLoadAddress(context: Context, latLng: LatLng) {
        _selectedLatLng.value = latLng
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