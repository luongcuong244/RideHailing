package com.cuongnl.ridehailing.retrofit.api

import com.cuongnl.ridehailing.models.LoginResponse
import com.cuongnl.ridehailing.models.ChangePasswordRequest
import com.cuongnl.ridehailing.models.ChangePasswordResponse
import com.cuongnl.ridehailing.models.LoginRequest
import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApi {
    @GET("auth/check-existing-user")
    fun checkExistingUser(@Query("phoneNumber") phoneNumber: String): Call<ScalarsBooleanResponse>

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("change-password")
    fun changePassword(@Body request: ChangePasswordRequest): Call<ChangePasswordResponse>
}