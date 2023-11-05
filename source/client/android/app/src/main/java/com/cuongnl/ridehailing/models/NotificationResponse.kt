package com.cuongnl.ridehailing.models

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("notifications") val notifications: List<Notification>
)
