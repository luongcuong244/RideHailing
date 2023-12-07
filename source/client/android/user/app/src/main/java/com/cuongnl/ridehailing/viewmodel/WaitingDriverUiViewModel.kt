package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.cuongnl.ridehailing.screens.home.HomeActivity

class WaitingDriverUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_NOTIFY_ARRIVED_AT_PICKUP = "notify-arrived-at-pickup"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _driverAcceptResponse: DriverAcceptResponse

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ARRIVED_AT_PICKUP) {
            val response = it[0].toString()
            val isSuccessful = response.toBoolean()

            if (isSuccessful) {

            } else {
                Toast.makeText(context, "Cannot notify arrived at pickup", Toast.LENGTH_SHORT).show()
            }
        }
    }

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