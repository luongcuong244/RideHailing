package com.cuongnl.ridehailing.models

data class User(
    val userName: String,
    val phoneNumber: String,
    val addresses: List<Address>,
)
