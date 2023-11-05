package com.cuongnl.ridehailing.retrofit.api

import com.cuongnl.ridehailing.models.AddressResponse
import com.cuongnl.ridehailing.models.ChangePasswordRequest
import com.cuongnl.ridehailing.models.ChangePasswordResponse
import com.cuongnl.ridehailing.models.GetUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("user/change-password")
    fun changePassword(@Body request: ChangePasswordRequest): Call<ChangePasswordResponse>

    @GET("user/current")
    fun getUser(): Call<GetUserResponse>

    @GET("user/get-addresses")
    fun getUserAddresses(): Call<AddressResponse>
}