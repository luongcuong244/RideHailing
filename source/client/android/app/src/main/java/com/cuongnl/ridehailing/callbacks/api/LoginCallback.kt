package com.cuongnl.ridehailing.callbacks.api

import com.cuongnl.ridehailing.models.GetUserResponse

interface LoginCallback<T> : BaseApiCallback<T> {
    fun onWrongPassword()
    fun onSuccessfulLogin(accessToken: String, userData: GetUserResponse)
    fun onNoAccessTokenProvided()
    fun onNoUserDataProvided()
}