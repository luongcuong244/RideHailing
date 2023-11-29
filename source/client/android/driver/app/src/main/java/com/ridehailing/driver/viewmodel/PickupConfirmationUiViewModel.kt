package com.ridehailing.driver.viewmodel

import androidx.lifecycle.ViewModel
import com.ridehailing.driver.models.TripInfo

class PickupConfirmationUiViewModel : ViewModel() {

    private lateinit var _currentTripInfo : TripInfo

    val currentTripInfo : TripInfo
        get() = _currentTripInfo

    fun setCurrentTripInfo(tripInfo : TripInfo) {
        _currentTripInfo = tripInfo
    }
}