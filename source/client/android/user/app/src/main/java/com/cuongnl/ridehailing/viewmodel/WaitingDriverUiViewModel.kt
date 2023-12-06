package com.cuongnl.ridehailing.viewmodel

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.api.DriverInfoResponse
import com.google.android.gms.maps.model.LatLng

class WaitingDriverUiViewModel : ViewModel() {

    private lateinit var _driverInfoResponse: DriverInfoResponse
    private var _destinationLatLng: LatLng = LatLng(0.0, 0.0)
    private var _minutesToDriverArrival: Int = 1

    fun setDriverInfoResponse(driverInfoResponse: DriverInfoResponse) {
        this._driverInfoResponse = driverInfoResponse
    }

    fun getDriverInfoResponse(): DriverInfoResponse {
        return _driverInfoResponse
    }

    fun setDestinationLatLng(destinationLatLng: LatLng) {
        this._destinationLatLng = destinationLatLng
    }

    fun getDestinationLatLng(): LatLng {
        return _destinationLatLng
    }

    fun setMinutesToDriverArrival(minutesToDriverArrival: Int) {
        this._minutesToDriverArrival = minutesToDriverArrival
    }

    fun getMinutesToDriverArrival(): Int {
        return _minutesToDriverArrival
    }
}