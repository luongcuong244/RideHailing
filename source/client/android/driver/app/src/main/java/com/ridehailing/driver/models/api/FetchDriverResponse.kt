package com.ridehailing.driver.models.api

import com.google.gson.annotations.SerializedName

data class FetchDriverResponse(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("name") val name: String,
)