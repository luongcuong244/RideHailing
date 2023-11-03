package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R

enum class TransportationType(
    val text: Int,
    val icon: Int,
) {
    TAXI(R.string.taxi_text, R.drawable.ic_taxi),
    BIKE(R.string.bike_text, R.drawable.ic_bike),
}