package com.cuongnl.ridehailing.models

import com.cuongnl.ridehailing.enums.AddressType

data class Address(
    val addressType: AddressType,
    val shortName: String,
    val fullName: String,
    val longitude: Double,
    val latitude: Double,
)
