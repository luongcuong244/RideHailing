package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.models.Address
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.models.UserInfo
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.screens.pickupconfirmation.PickupConfirmationActivity
import com.ridehailing.driver.utils.Constant
import org.json.JSONObject

class TripTabUiViewModel : ViewModel() {

    private companion object {
        const val EVENT_SEND_REQUESTING_A_RIDE = "send-requesting-a-ride-to-drivers"
    }

    private val trips = mutableStateListOf<TripInfo>()

    private var _selectedTrip = mutableStateOf<TripInfo?>(null)

    val selectedTrip: State<TripInfo?> = _selectedTrip

    private val mSocket = BookingSocket.socket

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_SEND_REQUESTING_A_RIDE) {
            val json = JSONObject(it[0].toString())
            val trip = TripInfo.fromJson(json)
            addTrip(trip)
        }
    }

    fun getTrips(): List<TripInfo> {
        return trips
    }

    fun addTrip(trip: TripInfo) {
        trips.add(0, trip)
    }

    fun removeTrip(trip: TripInfo) {
        trips.remove(trip)
    }

    fun removeTripById(tripId: String) {
        trips.removeIf { trip -> trip.id == tripId }
    }

    fun setSelectedTrip(trip: TripInfo?) {
        _selectedTrip.value = trip
    }

    fun clickToAcceptARide(context: Context) {
        val intent = Intent(context, PickupConfirmationActivity::class.java)
        intent.putExtra(Constant.BUNDLE_TRIP_INFO, _selectedTrip.value)
        context.startActivity(intent)
    }
}