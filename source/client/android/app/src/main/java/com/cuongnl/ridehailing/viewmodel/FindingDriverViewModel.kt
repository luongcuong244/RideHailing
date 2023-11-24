package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.api.RequestARideRequest
import com.cuongnl.ridehailing.network.socketio.SocketClient
import org.json.JSONObject


class FindingDriverViewModel : ViewModel() {

    private companion object {
        private const val TAG = "BookingActivitySocketViewModel"
        private const val NAMESPACE = "booking"
        private const val EVENT_REQUEST_A_RIDE = "request-a-ride"
    }

    private val mSocket = SocketClient.getSocket(NAMESPACE)

    fun setupListeners(context: Context) {
        mSocket?.on("response-a-ride") {

        }
    }

    fun emitRequestARide(data: JSONObject) {
        mSocket?.emit(EVENT_REQUEST_A_RIDE, data)
    }

    fun connect() {
        mSocket?.connect()
    }

    fun disconnect() {
        mSocket?.disconnect()
    }
}