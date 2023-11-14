package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.google.android.gms.maps.model.LatLng

class BookingActivityUiViewModel : ViewModel() {

    val points = mutableStateListOf<LatLng>()

    private val _destinationLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _pickupLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _destinationLocationAddress = mutableStateOf("")
    private val _pickupLocationAddress = mutableStateOf("")

    val destinationLocationLatLng: State<LatLng> = _destinationLocationLatLng
    val pickupLocationLatLng: State<LatLng> = _pickupLocationLatLng
    val destinationLocationAddress: State<String> = _destinationLocationAddress
    val pickupLocationAddress: State<String> = _pickupLocationAddress

    fun setPoints(points: List<LatLng>) {
        this.points.clear()
        this.points.addAll(points)
    }

    fun setDestinationLocationLatLng(destinationLocationLatLng: LatLng) {
        _destinationLocationLatLng.value = destinationLocationLatLng
    }

    fun setPickupLocationLatLng(pickupLocationLatLng: LatLng) {
        _pickupLocationLatLng.value = pickupLocationLatLng
    }

    fun setDestinationLocationAddress(destinationLocationAddress: String) {
        _destinationLocationAddress.value = destinationLocationAddress
    }

    fun setPickupLocationAddress(pickupLocationAddress: String) {
        _pickupLocationAddress.value = pickupLocationAddress
    }
}