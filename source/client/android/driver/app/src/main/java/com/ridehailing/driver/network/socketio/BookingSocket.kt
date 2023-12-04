package com.ridehailing.driver.network.socketio

import com.ridehailing.driver.globalstate.CurrentLocation
import org.json.JSONObject

object BookingSocket {

    private const val EVENT_DRIVER_CONNECT_TO_SOCKET = "driver-connect-socket"
    private const val EVENT_UPDATE_DRIVER_LOCATION = "update-driver-location"

    private var isConnected = false

    val socket = SocketClient.getSocket("/booking")

    fun connect() {
        if (!isConnected) {
            socket?.let {
                it.connect()
                isConnected = true
            }
        }
    }

    fun disconnect() {
        socket?.disconnect()
        isConnected = false
    }

    fun emitDriverConnectToSocket(data: JSONObject) {
        socket?.emit(EVENT_DRIVER_CONNECT_TO_SOCKET, data)
    }

    fun emitToUpdateDriverLocation() {

        val data = JSONObject()

        data.put("currentLatitude", CurrentLocation.latLng.value.latitude)
        data.put("currentLongitude", CurrentLocation.latLng.value.longitude)

        socket?.emit(EVENT_UPDATE_DRIVER_LOCATION, data)
    }
}