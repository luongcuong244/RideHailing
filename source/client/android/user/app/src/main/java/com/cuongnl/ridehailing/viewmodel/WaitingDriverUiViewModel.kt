package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.extensions.showDialog
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.cuongnl.ridehailing.screens.triptracking.TripTrackingActivity
import com.cuongnl.ridehailing.utils.Constant
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject

class WaitingDriverUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_NOTIFY_ARRIVED_AT_PICKUP = "notify-arrived-at-pickup"
        private const val EVENT_UPDATE_DRIVER_LOCATION = "notify-update-driver-location"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _driverAcceptResponse: DriverAcceptResponse

    private var _driverLocation = mutableStateOf(LatLng(0.0, 0.0))
    val driverLocation: State<LatLng> = _driverLocation

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ARRIVED_AT_PICKUP) {
            val response = it[0].toString()
            val isSuccessful = JSONObject(response).getBoolean("success")

            if (isSuccessful) {
                navigationToTripTrackingActivity(context)
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Cannot notify arrived at pickup", Toast.LENGTH_SHORT).show()
                }
            }
        }
        mSocket?.on(EVENT_UPDATE_DRIVER_LOCATION) {
            val response = it[0].toString()
            val json = JSONObject(response)

            val driverLocation = LatLng(
                json.getDouble("latitude"),
                json.getDouble("longitude")
            )
            setDriverLocation(driverLocation)
        }
    }

    fun setDriverLocation(driverLocation: LatLng) {
        this._driverLocation.value = driverLocation
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
        context.showDialog(
            title = context.getString(R.string.warning_text),
            message = context.getString(R.string.do_you_want_cancel_the_trip),
            textOfNegativeButton = context.getString(R.string.cancel_text),
            textOfPositiveButton = context.getString(R.string.ok_text),
            positiveButtonFunction = {
                BookingSocket.emitUserCancelTrip(_driverAcceptResponse.tripId)
            }
        )
    }

    private fun navigationToTripTrackingActivity(context: Context) {
        val intent = Intent(context, TripTrackingActivity::class.java)
        intent.putExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE, _driverAcceptResponse)
        intent.putExtra(Constant.BUNDLE_DRIVER_LOCATION, driverLocation.value)
        context.findActivity()?.finish()
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}