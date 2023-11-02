package com.cuongnl.ridehailing.viewmodel

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.callbacks.api.BaseApiCallback
import com.cuongnl.ridehailing.callbacks.api.LoginCallback
import com.cuongnl.ridehailing.callbacks.api.RetrofitCallback
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.callbacks.api.UserCheckCallback
import com.cuongnl.ridehailing.models.ChangePasswordRequest
import com.cuongnl.ridehailing.models.ChangePasswordResponse
import com.cuongnl.ridehailing.models.LoginResponse
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
        userCheckCallback: UserCheckCallback<ScalarsBooleanResponse>
    ) {
        authRepository.checkExistingUser(phoneNumber, object : RetrofitCallback<ScalarsBooleanResponse>(userCheckCallback) {
            override fun onResponse(
                call: Call<ScalarsBooleanResponse>,
                response: Response<ScalarsBooleanResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        if (it) {
                            userCheckCallback.onUserExisting()
                        } else {
                            userCheckCallback.onUserNotExisting()
                        }
                    }
                }
            }
        })
    }

    fun login(
        loginRequest: LoginRequest,
        loginCallback: LoginCallback<LoginResponse>
    ) {
        authRepository.login(loginRequest, object : RetrofitCallback<LoginResponse>(loginCallback) {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    // store access token and refresh token
                    loginCallback.onSuccessfulLogin()
                } else {
                    loginCallback.onWrongPassword()
                }
            }

            // need to remove
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                super.onFailure(call, t)
                loginCallback.onWrongPassword()
            }
        })
    }

    fun changePassword(
        changePasswordRequest: ChangePasswordRequest,
        simpleApiCallback: SimpleApiCallback<ChangePasswordResponse>
    ) {
        authRepository.changePassword(changePasswordRequest, object : RetrofitCallback<ChangePasswordResponse>(simpleApiCallback) {
        })
    }
}