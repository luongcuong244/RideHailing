package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("addresses") val addresses: List<Address>,
)
