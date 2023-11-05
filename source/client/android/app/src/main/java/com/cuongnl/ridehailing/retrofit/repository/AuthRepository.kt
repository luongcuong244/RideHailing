package com.cuongnl.ridehailing.retrofit.repository

import com.cuongnl.ridehailing.models.LoginRequest
import com.cuongnl.ridehailing.models.LoginResponse
import com.cuongnl.ridehailing.models.RegisterRequest
import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import com.cuongnl.ridehailing.retrofit.RetrofitClient
import com.cuongnl.ridehailing.retrofit.api.AuthApi
import retrofit2.Callback

class AuthRepository {

    private val authApi: AuthApi = RetrofitClient.getClient().create(AuthApi::class.java)

    fun checkExistingUser(phoneNumber: String, callback: Callback<ScalarsBooleanResponse>) {
        authApi.checkExistingUser(phoneNumber).enqueue(callback)
    }

    fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>) {
        authApi.login(loginRequest).enqueue(callback)
    }

    fun register(registerRequest: RegisterRequest, callback: Callback<ScalarsBooleanResponse>) {
        authApi.register(registerRequest).enqueue(callback)
    }
}