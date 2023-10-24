package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.CountryCode

class CountryCodeBottomSheetViewModel : ViewModel() {

    val phoneCountryCodes = mutableStateListOf<CountryCode>()
        .apply {
            addAll(CountryCode.values().toList())
        }

    private val _isBottomSheetVisible = mutableStateOf(false)

    val isBottomSheetVisible : State<Boolean> = _isBottomSheetVisible

    fun setBottomSheetVisible(isVisible: Boolean){
        _isBottomSheetVisible.value = isVisible
    }

    fun filterCountryCodeList(query: String){
        phoneCountryCodes.clear()

        if(query.isEmpty()){
            phoneCountryCodes.addAll(CountryCode.values().toList())
            return
        }

        phoneCountryCodes.addAll(CountryCode.values().filter {
            it.countryName.contains(query.trim(), true) || it.phoneCode.contains(query.trim(), true)
        })
    }
}