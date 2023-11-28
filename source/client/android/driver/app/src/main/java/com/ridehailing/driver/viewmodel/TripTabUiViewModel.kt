package com.ridehailing.driver.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.models.TripInfo
import com.ridehailing.driver.network.socketio.SocketClient

class TripTabUiViewModel : ViewModel() {

    private companion object {
        private const val NAMESPACE = "/booking"
        private const val EVENT_REQUEST_A_RIDE = "request-a-ride"
    }

    private val trips = mutableStateListOf<TripInfo>()
        .apply {
            add(TripInfo(
                id = "1",
                pickupAddress = "123 Nguyen Luong Bang",
                destinationAddress = "456 Nguyen Luong Bang",
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee"
            ))
            add(TripInfo(
                id = "2",
                pickupAddress = "123 Nguyen Luong Bang",
                destinationAddress = "456 Nguyen Luong Bang",
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee"
            ))
            add(TripInfo(
                id = "3",
                pickupAddress = "123 Nguyen Luong Bang",
                destinationAddress = "456 Nguyen Luong Bang",
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee"
            ))
            add(TripInfo(
                id = "4",
                pickupAddress = "123 Nguyen Luong Bang",
                destinationAddress = "456 Nguyen Luong Bang",
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee"
            ))
            add(TripInfo(
                id = "5",
                pickupAddress = "123 Nguyen Luong Bang",
                destinationAddress = "456 Nguyen Luong Bang",
                cost = 15,
                distanceInKilometers = 1.0,
                durationInMinutes = 10,
                minutesToDriverArrival = 5,
                kilometersToDriverArrival = 0.5,
                paymentMethod = "Cash",
                noteForDriver = "Please bring me a coffee"
            ))
        }

    private var _selectedTrip = mutableStateOf<TripInfo?>(null)

    val selectedTrip: State<TripInfo?> = _selectedTrip

    private val mSocket = SocketClient.getSocket(NAMESPACE)

    fun setupListeners(context: Context) {

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

    }
}