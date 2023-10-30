package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PasswordVerificationViewModel : ViewModel() {

    private var _internationalPhoneNumber = mutableStateOf("")
    private var _isWrongPassword = mutableStateOf(false)

    val internationalPhoneNumber: State<String> = _internationalPhoneNumber
    val isWrongPassword: State<Boolean> = _isWrongPassword

    fun setInternationalPhoneNumber(phoneNumber: String) {
        _internationalPhoneNumber.value = phoneNumber
    }

    fun setIsWrongPassword(isWrongPassword: Boolean) {
        _isWrongPassword.value = isWrongPassword
    }
}