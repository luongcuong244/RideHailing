package com.cuongnl.ridehailing.core

import com.cuongnl.ridehailing.retrofit.RetrofitClient

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.createClient(applicationContext)
    }

}