package com.cuongnl.ridehailing.viewmodel

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.api.DriverInfoResponse

class WaitingDriverUiViewModel : ViewModel() {

    private lateinit var _driverInfoResponse: DriverInfoResponse

    fun setDriverInfoResponse(driverInfoResponse: DriverInfoResponse) {
        this._driverInfoResponse = driverInfoResponse
    }

    fun getDriverInfoResponse(): DriverInfoResponse {
        return _driverInfoResponse
    }
}