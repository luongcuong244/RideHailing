package com.cuongnl.ridehailing.retrofit.repository

import com.cuongnl.ridehailing.models.api.GetBookingInfoRequest
import com.cuongnl.ridehailing.models.api.GetBookingInfoResponse
import com.cuongnl.ridehailing.models.api.RequestARideRequest
import com.cuongnl.ridehailing.models.api.RequestARideResponse
import com.cuongnl.ridehailing.retrofit.RetrofitClient
import com.cuongnl.ridehailing.retrofit.api.BookingApi
import retrofit2.Callback

class BookingRepository {

    private val bookingApi: BookingApi = RetrofitClient.getClient().create(BookingApi::class.java)

    fun getBookingInfo(bookingInfoRequest: GetBookingInfoRequest, callback: Callback<GetBookingInfoResponse>) {
        bookingApi.getBookingInfo(bookingInfoRequest).enqueue(callback)
    }

    fun requestARide(request: RequestARideRequest, callback: Callback<RequestARideResponse>) {
        bookingApi.requestARide(request).enqueue(callback)
    }
}