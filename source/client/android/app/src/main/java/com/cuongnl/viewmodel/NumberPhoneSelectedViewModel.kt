package com.cuongnl.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.enums.NumberPhone

class NumberPhoneSelectedViewModel : ViewModel() {

    private val _currentNumberPhone = mutableStateOf(NumberPhone.VIET_NAM)

    val currentNumberPhone : State<NumberPhone> = _currentNumberPhone

    fun setCurrentNumberPhone(numberPhone: NumberPhone){
        _currentNumberPhone.value = numberPhone
    }
}