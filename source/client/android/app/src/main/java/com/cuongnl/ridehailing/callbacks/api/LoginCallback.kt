package com.cuongnl.ridehailing.callbacks.api

interface LoginCallback<T> : BaseApiCallback<T> {
    fun onWrongPassword()
    fun onSuccessfulLogin()
}