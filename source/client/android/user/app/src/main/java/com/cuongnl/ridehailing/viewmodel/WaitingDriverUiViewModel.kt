package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.screens.home.HomeActivity

class WaitingDriverUiViewModel : ViewModel() {

    private lateinit var _driverAcceptResponse: DriverAcceptResponse

    fun setDriverAcceptResponse(driverAcceptResponse: DriverAcceptResponse) {
        this._driverAcceptResponse = driverAcceptResponse
    }

    fun getDriverAcceptResponse(): DriverAcceptResponse {
        return _driverAcceptResponse
    }

    fun onClickTextingButton(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("smsto:${_driverAcceptResponse.driverInfo.phoneNumber}")
        context.startActivity(intent)
    }

    fun getVehicleIconId(): Int {
        return when(_driverAcceptResponse.driverInfo.travelMode) {
            TransportationType.TAXI.name -> TransportationType.TAXI.icon
            TransportationType.BIKE.name -> TransportationType.BIKE.icon
            else -> TransportationType.BIKE.icon
        }
    }

    fun onClickCallButton(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${_driverAcceptResponse.driverInfo.phoneNumber}")
        context.startActivity(intent)
    }

    fun clickBackButton(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
        context.findActivity()?.finish()
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}