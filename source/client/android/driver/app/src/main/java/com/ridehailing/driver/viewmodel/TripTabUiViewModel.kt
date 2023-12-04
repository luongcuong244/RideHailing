package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.globalstate.CurrentDriver
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.models.Address
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.models.UserInfo
import com.ridehailing.driver.models.api.DriverConnectToSocket
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.network.socketio.BookingSocket.emitDriverConnectToSocket
import com.ridehailing.driver.network.socketio.SocketClient
import com.ridehailing.driver.screens.pickupconfirmation.PickupConfirmationActivity
import com.ridehailing.driver.utils.Constant
import org.json.JSONObject

class TripTabUiViewModel : ViewModel() {

    private companion object {
        const val EVENT_SEND_REQUESTING_A_RIDE = "send-requesting-a-ride-to-drivers"
    }

    private val trips = mutableStateListOf<TripInfo>()
        .apply {
            add(TripInfo(
                id = "1",
                pickupAddress = Address(
                    "123 Nguyen Luong Bang",
                    20.9808114, 
                    105.7936536
                ),
                destinationAddress = Address(
                    "456 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee",
                userInfo = UserInfo(
                    userName = "Cuong Nguyen",
                    phoneNumber = "123456789"
                )
            ))
            add(TripInfo(
                id = "2",
                pickupAddress = Address(
                    "123 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                destinationAddress = Address(
                    "456 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee",
                userInfo = UserInfo(
                    userName = "Cuong Nguyen",
                    phoneNumber = "123456789"
                )
            ))
            add(TripInfo(
                id = "3",
                pickupAddress = Address(
                    "123 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                destinationAddress = Address(
                    "456 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee",
                userInfo = UserInfo(
                    userName = "Cuong Nguyen",
                    phoneNumber = "123456789"
                )
            ))
            add(TripInfo(
                id = "4",
                pickupAddress = Address(
                    "123 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                destinationAddress = Address(
                    "456 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee",
                userInfo = UserInfo(
                    userName = "Cuong Nguyen",
                    phoneNumber = "123456789"
                )
            ))
            add(TripInfo(
                id = "5",
                pickupAddress = Address(
                    "123 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                destinationAddress = Address(
                    "456 Nguyen Luong Bang",
                    20.9808114,
                    105.7936536
                ),
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee",
                userInfo = UserInfo(
                    userName = "Cuong Nguyen",
                    phoneNumber = "123456789"
                )
            ))
        }

    private var _selectedTrip = mutableStateOf<TripInfo?>(null)

    val selectedTrip: State<TripInfo?> = _selectedTrip

    private val mSocket = BookingSocket.socket

    fun setupListeners(context: Context) {
        mSocket?.on(EVENT_SEND_REQUESTING_A_RIDE) {
            Log.d("DDDDDDDD", it.toString())
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