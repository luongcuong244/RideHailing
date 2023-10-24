package com.cuongnl.ridehailing.viewmodel

import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.ScalarsBooleanResponse
import com.cuongnl.ridehailing.retrofit.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthServiceViewModel : ViewModel() {

    private var authRepository: AuthRepository = AuthRepository()

    fun checkExistingUser(
        numberPhone: String,
        onError: () -> Unit = {},
        onUserExisting: () -> Unit = {},
        onUserNotExisting: () -> Unit = {}
    ) {

        onUserNotExisting()
        return

        authRepository.checkExistingUser(numberPhone, object: Callback<ScalarsBooleanResponse> {
            override fun onResponse(call: Call<ScalarsBooleanResponse>, response: Response<ScalarsBooleanResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        if(it){
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
}