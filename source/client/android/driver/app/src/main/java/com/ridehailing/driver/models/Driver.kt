package com.ridehailing.driver.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class Driver {

    private var _name : MutableState<String?> = mutableStateOf(null)
    private var _phoneNumber : MutableState<String?> = mutableStateOf(null)

    val name : State<String?> = _name
    val phoneNumber : State<String?> = _phoneNumber

    fun setName(userName: String) {
        _name.value = userName
    }

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }
}
