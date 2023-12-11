package com.cuongnl.ridehailing.models.api

import com.cuongnl.ridehailing.models.Bill
import com.google.gson.annotations.SerializedName

data class GetBillsResponse(
    @SerializedName("bills") val bills: List<Bill>,
)
