package com.cuongnl.ridehailing.network.socketio

import com.cuongnl.ridehailing.globalstate.CurrentUser
import org.json.JSONObject

object BookingSocket {

    private const val EVENT_FIND_A_DRIVER = "find-a-driver"
    private const val EVENT_USER_CONNECT_SOCKET = "user-connect-socket"

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

    fun emitUserConnectSocket() {
        val json = JSONObject()
        json.put("phoneNumber", CurrentUser.getUser()?.phoneNumber?.value)

        socket?.emit(EVENT_USER_CONNECT_SOCKET, json)
    }

    fun emitFindADriver(data: JSONObject) {
        socket?.emit(EVENT_FIND_A_DRIVER, data)
    }
}