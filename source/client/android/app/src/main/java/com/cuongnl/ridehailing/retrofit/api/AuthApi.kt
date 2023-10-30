package com.cuongnl.ridehailing.retrofit.api

import com.cuongnl.ridehailing.models.AuthResponse
import com.cuongnl.ridehailing.models.LoginRequest
import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @GET("phones/{phoneNumber}")
    fun checkExistingUser(@Path("phoneNumber") phoneNumber: String): Call<ScalarsBooleanResponse>

    @POST("login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>
}