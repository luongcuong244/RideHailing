package com.cuongnl.ridehailing.network.retrofit.api

import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.models.api.AddressResponse
import com.cuongnl.ridehailing.models.api.ChangePasswordRequest
import com.cuongnl.ridehailing.models.api.ChangePasswordResponse
import com.cuongnl.ridehailing.models.api.GetBillsResponse
import com.cuongnl.ridehailing.models.api.GetUserResponse
import com.cuongnl.ridehailing.models.api.UpdateProfileRequest
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

    @POST("user/add-address")
    fun addAddress(@Body request: Address): Call<Void>

    @GET("user/get-bills")
    fun getBills(): Call<GetBillsResponse>

    @POST("user/update-profile")
    fun updateProfile(@Body request: UpdateProfileRequest): Call<Void>
}