package com.cuongnl.ridehailing.retrofit.repository

import com.cuongnl.ridehailing.models.api.NotificationResponse
import com.cuongnl.ridehailing.retrofit.RetrofitClient
import com.cuongnl.ridehailing.retrofit.api.NotificationApi
import retrofit2.Callback

class NotificationRepository {

    private val notificationApi: NotificationApi = RetrofitClient.getClient().create(NotificationApi::class.java)

    fun getNotifications(callback: Callback<NotificationResponse>) {
        notificationApi.getNotifications().enqueue(callback)
    }
}