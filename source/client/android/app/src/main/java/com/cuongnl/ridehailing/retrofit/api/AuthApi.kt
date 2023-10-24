package com.cuongnl.ridehailing.retrofit.api

import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("phones/{numberPhone}")
    fun checkExistingUser(@Path("numberPhone") numberPhone: String): Call<ScalarsBooleanResponse>
}