package com.ridehailing.driver.models

data class TripInfo(
    val id: String,
    val pickupAddress: String,
    val destinationAddress: String,
    val distanceInKilometers: Double,
    val durationInMinutes: Int,
    val minutesToDriverArrival: Int,
    val kilometersToDriverArrival: Double,
    val paymentMethod: String,
    val noteForDriver: String,
    val cost: Int,
)
