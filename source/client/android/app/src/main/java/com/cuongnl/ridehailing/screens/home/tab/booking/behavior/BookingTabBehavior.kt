package com.cuongnl.ridehailing.screens.home.tab.booking.behavior

import android.content.Context
import android.content.Intent
import com.cuongnl.ridehailing.screens.confirmlocation.ConfirmLocationActivity

class BookingTabBehavior(val context: Context) {

    fun navigateToConfirmLocationScreen() {
        val intent = Intent(context, ConfirmLocationActivity::class.java)
        context.startActivity(intent)
    }

}