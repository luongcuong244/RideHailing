package com.cuongnl.ridehailing.retrofit.repository

import com.cuongnl.ridehailing.models.api.NotificationResponse
import com.cuongnl.ridehailing.models.api.RemoveNotificationsRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import com.cuongnl.ridehailing.retrofit.RetrofitClient
import com.cuongnl.ridehailing.retrofit.api.NotificationApi
import retrofit2.Callback

class NotificationRepository {

    private val notificationApi: NotificationApi = RetrofitClient.getClient().create(NotificationApi::class.java)

    fun getNotifications(callback: Callback<NotificationResponse>) {
        notificationApi.getNotifications().enqueue(callback)
    }

    fun removeAllNotification(callback: Callback<ScalarsBooleanResponse>) {
        notificationApi.removeAllNotification().enqueue(callback)
    }

    fun removeNotifications(request: RemoveNotificationsRequest, callback: Callback<ScalarsBooleanResponse>) {
        notificationApi.removeNotifications(request).enqueue(callback)
    }
}