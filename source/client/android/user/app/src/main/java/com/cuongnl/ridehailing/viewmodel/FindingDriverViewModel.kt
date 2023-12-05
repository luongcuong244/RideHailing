package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.PaymentMethod
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.network.socketio.SocketClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class FindingDriverViewModel : ViewModel() {

    private companion object {
        private const val TAG = "BookingActivitySocketViewModel"
        private const val NAMESPACE = "/booking"
        private const val EVENT_FIND_A_DRIVER = "find-a-driver"
        private const val EVENT_DRIVER_ACCEPTED_TRIP = "driver-accepted-trip"
        private const val EVENT_RECEIVE_TRIP_BOOKING_RECORD_ID = "receive-trip-booking-record"
        private const val EVENT_USER_CONNECT_SOCKET = "user-connect-socket"
    }

    private var _dotCount: MutableState<Int> = mutableStateOf(0)
    val dotCount: State<Int> = _dotCount

    private val mSocket = SocketClient.getSocket(NAMESPACE)

    private lateinit var pickupLatLng: LatLng
    private lateinit var transportationType: TransportationType
    private lateinit var paymentMethod: PaymentMethod
    private var cost: Int = 0

    private var tripBookingRecordIdJson : JSONObject? = null

    init {
        runDotAnimation()
    }

    private fun runDotAnimation() {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                setDotCount((dotCount.value + 1) % 3)
                withContext(Dispatchers.IO) {
                    Thread.sleep(500)
                }
            }
        }
    }

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_DRIVER_ACCEPTED_TRIP) {

        }
        mSocket?.on(EVENT_RECEIVE_TRIP_BOOKING_RECORD_ID) {
            val json = JSONObject(it[0].toString())
            tripBookingRecordIdJson = json
        }
    }

    fun offListeners() {
        mSocket?.off(EVENT_DRIVER_ACCEPTED_TRIP)
        mSocket?.off(EVENT_RECEIVE_TRIP_BOOKING_RECORD_ID)
    }

    fun emitUserConnectSocket() {

        val json = JSONObject()
        json.put("phoneNumber", CurrentUser.getUser()?.phoneNumber)

        mSocket?.emit(EVENT_USER_CONNECT_SOCKET, json)
    }

    fun emitFindADriver(data: JSONObject) {
        mSocket?.emit(EVENT_FIND_A_DRIVER, data)
    }

    fun connect() {
        mSocket?.connect()
    }

    fun disconnect() {
        mSocket?.disconnect()
    }

    fun setDotCount(dotCount: Int) {
        _dotCount.value = dotCount
    }

    fun getPickupLatLng(): LatLng {
        return pickupLatLng
    }

    fun setPickupLatLng(pickupLatLng: LatLng) {
        this.pickupLatLng = pickupLatLng
    }

    fun getTransportationType(): TransportationType {
        return transportationType
    }

    fun setTransportationType(transportationType: TransportationType) {
        this.transportationType = transportationType
    }

    fun getPaymentMethod(): PaymentMethod {
        return paymentMethod
    }

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        this.paymentMethod = paymentMethod
    }

    fun getCost(): Int {
        return cost
    }

    fun setCost(cost: Int) {
        this.cost = cost
    }
}