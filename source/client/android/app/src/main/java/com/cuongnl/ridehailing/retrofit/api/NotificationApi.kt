package com.cuongnl.ridehailing.retrofit.api

import com.cuongnl.ridehailing.models.api.NotificationResponse
import retrofit2.Call
import retrofit2.http.GET

interface NotificationApi {
    @GET("notification/get-notifications")
    fun getNotifications() : Call<NotificationResponse>
}