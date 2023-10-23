package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.NumberPhone

class CountryCodeBottomSheetViewModel : ViewModel() {

    val phoneCountryCodes = mutableStateListOf(NumberPhone.values())

    private val _isBottomSheetVisible = mutableStateOf(false)

    val isBottomSheetVisible : State<Boolean> = _isBottomSheetVisible

    fun setBottomSheetVisible(isVisible: Boolean){
        _isBottomSheetVisible.value = isVisible
    }
}