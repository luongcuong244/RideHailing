package com.ridehailing.driver.globalstate

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.maps.model.LatLng
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.utils.MapUtils
import org.json.JSONObject

object CurrentLocation {

    private var _latLng = mutableStateOf(LatLng(20.9808164, 105.7936536))
    val latLng: State<LatLng> = _latLng

    private var _address = mutableStateOf("")
    val address: State<String> = _address

    fun fetchAddress(context: Context) {

        MapUtils.getAddressByCoordinates(
            context,
            latLng.value,
            onSuccess = {
                setAddress(it)
            },
            onFailure = {
                Toast.makeText(context, "Cannot get current address", Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun setLatLngAndUpdateToServer(latLng: LatLng, data: JSONObject? = null) {
        _latLng.value = latLng
        BookingSocket.emitToUpdateDriverLocation(data)
    }

    fun setAddress(address: String) {
        _address.value = address
    }
}