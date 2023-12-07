package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.screens.home.HomeActivity

class TripDetailsUiViewModel : ViewModel() {

    private lateinit var _driverAcceptResponse: DriverAcceptResponse
    private var cost = 0

    private val _starNumber = mutableStateOf(0)
    private val _isSendingRating = mutableStateOf(false)

    val starNumber: State<Int> = _starNumber
    val isSendingRating: State<Boolean> = _isSendingRating

    fun onClickBackButton(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun onRatingDriver(context: Context) {
        setSendingRating(true)
    }

    fun setStarNumber(number: Int) {
        _starNumber.value = number
    }

    fun setSendingRating(isSending: Boolean) {
        _isSendingRating.value = isSending
    }

    fun getDriverAcceptResponse(): DriverAcceptResponse {
        return _driverAcceptResponse
    }

    fun setDriverAcceptResponse(driverAcceptResponse: DriverAcceptResponse) {
        this._driverAcceptResponse = driverAcceptResponse
    }

    fun setCost(value: Int) {
        cost = value
    }

    fun getCost(): Int {
        return cost
    }
}