package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.screens.editprofile.EditProfileActivity
import com.cuongnl.ridehailing.screens.savedaddress.SavedAddressActivity

class AccountTabUiViewModel : ViewModel() {

    fun navigateToEditProfile(context: Context) {
        val intent = Intent(context, EditProfileActivity::class.java)
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun navigateToSavedAddress(context: Context) {
        val intent = Intent(context, SavedAddressActivity::class.java)
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}