package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.cuongnl.ridehailing.screens.tripdetails.TripDetailsActivity
import com.cuongnl.ridehailing.utils.Constant
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject

class TripTrackingUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_NOTIFY_TRIP_COMPLETED = "notify-trip-completed"
        private const val EVENT_UPDATE_DRIVER_LOCATION = "notify-update-driver-location"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _driverAcceptResponse: DriverAcceptResponse
    private var cost = 0

    private var _driverLocation = mutableStateOf(LatLng(0.0, 0.0))

    val driverLocation: State<LatLng> = _driverLocation

    fun setupListeners(context: Context) {
        Log.d("TripTrackingUiViewModel", "setupListeners: ")
        mSocket?.on(EVENT_NOTIFY_TRIP_COMPLETED) {
            val response = it[0].toString()
            val isSuccessful = JSONObject(response).getBoolean("success")
            cost = JSONObject(response).getInt("cost")

            if (isSuccessful) {
                navigateToTripDetailsActivity(context)
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

    fun setDriverAcceptResponse(driverAcceptResponse: DriverAcceptResponse) {
        this._driverAcceptResponse = driverAcceptResponse
    }

    fun getDriverAcceptResponse(): DriverAcceptResponse {
        return _driverAcceptResponse
    }

    fun getVehicleIconId(): Int {
        return when(_driverAcceptResponse.driverInfo.travelMode) {
            TransportationType.TAXI.name -> TransportationType.TAXI.icon
            TransportationType.BIKE.name -> TransportationType.BIKE.icon
            else -> TransportationType.BIKE.icon
        }
    }

    fun setDriverLocation(latLng: LatLng) {
        _driverLocation.value = latLng
    }

    fun navigateToTripDetailsActivity(context: Context) {
        val intent = Intent(context, TripDetailsActivity::class.java)
        intent.putExtra(Constant.BUNDLE_DRIVER_ACCEPT_RESPONSE, _driverAcceptResponse)
        intent.putExtra(Constant.BUNDLE_COST, cost)
        context.startActivity(intent)
    }
}