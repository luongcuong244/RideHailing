package com.cuongnl.ridehailing.viewmodel

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.api.RequestARideRequest
import com.cuongnl.ridehailing.network.socketio.SocketClient
import com.cuongnl.ridehailing.screens.booking.BookingActivity
import com.cuongnl.ridehailing.screens.findingdriver.FindingDriverActivity

class FindingDriverActivitySocketViewModel : ViewModel() {

    private companion object {
        private const val TAG = "BookingActivitySocketViewModel"
        private const val NAMESPACE = "booking"
    }

    private val mSocket = SocketClient.getSocket(NAMESPACE)

    fun setupListeners(findingDriverActivity: FindingDriverActivity) {
        mSocket?.on("response-a-ride") {

        }
    }

    fun emitRequestARide(request: RequestARideRequest) {
        mSocket?.emit("request-a-ride", request)
    }

    fun connect() {
        mSocket?.connect()
    }

    fun disconnect() {
        mSocket?.disconnect()
    }
}