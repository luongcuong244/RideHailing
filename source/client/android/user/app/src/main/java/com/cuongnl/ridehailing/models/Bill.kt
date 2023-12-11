package com.cuongnl.ridehailing.models

import com.cuongnl.ridehailing.models.api.DriverInfoResponse
import com.google.gson.annotations.SerializedName

data class Bill(
    @SerializedName("id") val id: String,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("pickupLatitude") val pickupLatitude: Double,
    @SerializedName("pickupLongitude") val pickupLongitude: Double,
    @SerializedName("destinationAddress") val destinationAddress: String,
    @SerializedName("destinationLatitude") val destinationLatitude: Double,
    @SerializedName("destinationLongitude") val destinationLongitude: Double,
    @SerializedName("distanceInKilometers") val distanceInKilometers: Double,
    @SerializedName("durationInMinutes") val durationInMinutes: Double,
    @SerializedName("paymentMethod") val paymentMethod: String,
    @SerializedName("noteForDriver") val noteForDriver: String,
    @SerializedName("cost") val cost: Int,
    @SerializedName("travelMode") val travelMode: String,
    @SerializedName("driverInfo") val driverInfo: DriverInfoResponse,
    @SerializedName("createdTime") val createdTime: Long
)
