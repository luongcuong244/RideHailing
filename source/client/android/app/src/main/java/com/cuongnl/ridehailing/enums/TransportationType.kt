package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R
import com.google.maps.model.TravelMode

enum class TransportationType(
    val text: Int,
    val icon: Int,
    val travelMode: TravelMode,
    val globalName: String,
    val characteristicText: Int
) {
    TAXI(
        R.string.taxi_text,
        R.drawable.ic_taxi,
        TravelMode.DRIVING,
        "Cam SM Taxi",
        R.string.taxi_characteristic_text,
    ),
    BIKE(
        R.string.bike_text,
        R.drawable.ic_bike,
        TravelMode.BICYCLING,
        "Cam SM Bike",
        R.string.bike_characteristic_text,
    ),
}