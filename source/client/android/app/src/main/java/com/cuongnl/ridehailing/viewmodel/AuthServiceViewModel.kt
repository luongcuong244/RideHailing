package com.cuongnl.ridehailing.viewmodel

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.AuthResponse
import com.cuongnl.ridehailing.models.LoginRequest
import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import com.cuongnl.ridehailing.retrofit.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthServiceViewModel : ViewModel() {

    private var authRepository: AuthRepository = AuthRepository()

    fun checkExistingUser(
        phoneNumber: String,
        onError: () -> Unit = {},
        onUserExisting: () -> Unit = {},
        onUserNotExisting: () -> Unit = {}
    ) {

        onUserNotExisting()
        return

        authRepository.checkExistingUser(phoneNumber, object : Callback<ScalarsBooleanResponse> {
            override fun onResponse(
                call: Call<ScalarsBooleanResponse>,
                response: Response<ScalarsBooleanResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        if (it) {
                            onUserExisting()
                        } else {
                            onUserNotExisting()
                        }
                    }
                } else {
                    onError()
                }
            }

            override fun onFailure(call: Call<ScalarsBooleanResponse>, t: Throwable) {
                onError()
            }
        })
    }

    fun login(
        loginRequest: LoginRequest,
        onFailure: () -> Unit = {},
        onSuccess: () -> Unit = {},
        onFinish: () -> Unit = {},
        onWrongPassword: () -> Unit = {}
    ) {

        onSuccess()
        onFinish()
        return

        authRepository.login(loginRequest, object : Callback<AuthResponse> {
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ) {
                if (response.isSuccessful) {
                    // store access token and refresh token
                    onSuccess()
                } else {
                    onWrongPassword()
                }
                onFinish()
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                onFailure()
                onFinish()
            }
        })
    }
}