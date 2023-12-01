package com.ridehailing.driver.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.screens.confirmlocation.ConfirmLocationActivity

class InfoTabUiViewModel(application: Application) : AndroidViewModel(application) {

    fun clickChangeLocation(context: Context) {
        val intent = Intent(context, ConfirmLocationActivity::class.java)
        context.startActivity(intent)
    }
}