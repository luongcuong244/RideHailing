package com.ridehailing.driver.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class Driver {

    private var _phoneNumber : MutableState<String?> = mutableStateOf(null)
    private var _driverName : MutableState<String?> = mutableStateOf(null)
    private var _driverAvatar : MutableState<String?> = mutableStateOf(null)
    private var _licensePlate : MutableState<String?> = mutableStateOf(null)
    private var _vehicleName : MutableState<String?> = mutableStateOf(null)
    private var _vehicleType : MutableState<String?> = mutableStateOf(null)

    val phoneNumber : State<String?> = _phoneNumber
    val driverName : State<String?> = _driverName
    val driverAvatar : State<String?> = _driverAvatar
    val licensePlate : State<String?> = _licensePlate
    val vehicleName : State<String?> = _vehicleName
    val vehicleType : State<String?> = _vehicleType

    fun setPhoneNumber(phoneNumber: String?) {
        _phoneNumber.value = phoneNumber
    }

    fun setDriverName(driverName: String?) {
        _driverName.value = driverName
    }

    fun setDriverAvatar(driverAvatar: String?) {
        _driverAvatar.value = driverAvatar
    }

    fun setLicensePlate(licensePlate: String?) {
        _licensePlate.value = licensePlate
    }

    fun setVehicleName(vehicleName: String?) {
        _vehicleName.value = vehicleName
    }

    fun setVehicleType(vehicleType: String?) {
        _vehicleType.value = vehicleType
    }
}
