package com.cuongnl.ridehailing.models.api

import com.cuongnl.ridehailing.enums.PaymentMethod
import com.google.gson.annotations.SerializedName

data class RequestARideRequest(
    @SerializedName("destinationLatitude") val destinationLatitude: Double,
    @SerializedName("destinationLongitude") val destinationLongitude: Double,
    @SerializedName("destinationAddress") val destinationAddress: String,
    @SerializedName("pickupLatitude") val pickupLatitude: Double,
    @SerializedName("pickupLongitude") val pickupLongitude: Double,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("paymentMethod") val paymentMethod: PaymentMethod,
    @SerializedName("noteForDriver") val noteForDriver: String,
    @SerializedName("transportationType") val transportationType: String,
    @SerializedName("distance") val distance: Double,
    @SerializedName("duration") val duration: Double,
    @SerializedName("distanceToPickup") val distanceToPickup: Double,
    @SerializedName("durationToPickup") val durationToPickup: Double,
    @SerializedName("cost") val cost: Int,
)
