package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.TravelMode

class BookingActivityUiViewModel : ViewModel() {

    val points = mutableStateListOf<LatLng>()

    private val _destinationLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _pickupLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _destinationLocationAddress = mutableStateOf("")
    private val _pickupLocationAddress = mutableStateOf("")
    private val _travelMode = mutableStateOf(TravelMode.DRIVING)

    val destinationLocationLatLng: State<LatLng> = _destinationLocationLatLng
    val pickupLocationLatLng: State<LatLng> = _pickupLocationLatLng
    val destinationLocationAddress: State<String> = _destinationLocationAddress
    val pickupLocationAddress: State<String> = _pickupLocationAddress
    val travelMode: State<TravelMode> = _travelMode

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

    fun setTravelModeAndUpdateUI(context: Context, travelMode: TravelMode) {
        _travelMode.value = travelMode

        getDirectionsBetweenTwoPoints(context)
    }

    private fun getDirectionsBetweenTwoPoints(context: Context) {

        val result = MapUtils.getDirectionsBetweenTwoPoints(
            destinationLocationLatLng.value,
            pickupLocationLatLng.value,
            travelMode.value
        )

        if (result.routes.isNotEmpty()) {

            val points = mutableListOf<LatLng>()

            result.routes[0].legs[0].steps.forEach {
                points.addAll(it.polyline.decodePath().map { latLng ->
                    val newLat = LatLng(latLng.lat, latLng.lng)
                    newLat
                })
            }

            setPoints(points)
        } else {
            Toast.makeText(context, "Cannot get directions", Toast.LENGTH_SHORT).show()
        }
    }
}