package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.models.api.DriverAcceptResponse
import com.cuongnl.ridehailing.network.socketio.BookingSocket
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject

class TripTrackingUiViewModel : ViewModel() {

    companion object {
        private const val EVENT_NOTIFY_ARRIVED_AT_DESTINATION = "notify-arrived-at-destination"
    }

    private var mSocket = BookingSocket.socket

    private lateinit var _driverAcceptResponse: DriverAcceptResponse

    private var _driverLocation = mutableStateOf(LatLng(0.0, 0.0))

    val driverLocation: State<LatLng> = _driverLocation

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_NOTIFY_ARRIVED_AT_DESTINATION) {
            val response = it[0].toString()
            val isSuccessful = JSONObject(response).getBoolean("success")

            if (isSuccessful) {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Arrived at destination", Toast.LENGTH_SHORT).show()
                }
            } else {
                context.findActivity()?.runOnUiThread {
                    Toast.makeText(context, "Cannot notify arrived at pickup", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun removeListeners() {
        mSocket?.off(EVENT_NOTIFY_ARRIVED_AT_DESTINATION)
    }

    fun setDriverAcceptResponseAndUpdateDriverLocation(driverAcceptResponse: DriverAcceptResponse) {
        this._driverAcceptResponse = driverAcceptResponse

        val driverLocation = LatLng(
            driverAcceptResponse.driverInfo.currentLatitude,
            driverAcceptResponse.driverInfo.currentLongitude
        )

        setDriverLocation(driverLocation)
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
}