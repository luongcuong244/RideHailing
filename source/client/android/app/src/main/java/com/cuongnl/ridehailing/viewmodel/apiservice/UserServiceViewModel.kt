package com.cuongnl.ridehailing.viewmodel.apiservice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.callbacks.api.RetrofitCallback
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.models.ChangePasswordRequest
import com.cuongnl.ridehailing.models.ChangePasswordResponse
import com.cuongnl.ridehailing.models.GetUserResponse
import com.cuongnl.ridehailing.retrofit.repository.UserRepository
import retrofit2.Call
import retrofit2.Response

class UserServiceViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository =
        UserRepository(getApplication<Application>().applicationContext)

    fun changePassword(
        changePasswordRequest: ChangePasswordRequest,
        simpleApiCallback: SimpleApiCallback<ChangePasswordResponse>
    ) {
        userRepository.changePassword(
            changePasswordRequest,
            object : RetrofitCallback<ChangePasswordResponse>(simpleApiCallback) {
                override fun onResponse(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {
                    super.onResponse(call, response)
                    if (response.isSuccessful) {
                        simpleApiCallback.onSuccess(call, response)
                    }
                }
            })
    }

    fun getUser(simpleApiCallback: SimpleApiCallback<GetUserResponse>) {
        userRepository.getUser(
            object : RetrofitCallback<GetUserResponse>(simpleApiCallback) {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    super.onResponse(call, response)
                    if (response.isSuccessful) {
                        simpleApiCallback.onSuccess(call, response)
                    }
                }
            })
    }
}