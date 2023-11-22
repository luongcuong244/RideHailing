package com.cuongnl.ridehailing.models.api

data class RequestARideResponse(
    val success: Boolean,
    val message: String,
    // val data: RequestARideData, ( driver info )
)
