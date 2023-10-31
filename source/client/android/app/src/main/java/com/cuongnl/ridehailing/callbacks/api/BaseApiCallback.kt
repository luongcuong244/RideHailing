package com.cuongnl.ridehailing.callbacks.api

import retrofit2.Call
import retrofit2.Response

interface BaseApiCallback<T> {
    fun onFinish()
    fun onError(call: Call<T>, response: Response<T>)
    fun onFailure(call: Call<T>, t: Throwable)
}