package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
)
