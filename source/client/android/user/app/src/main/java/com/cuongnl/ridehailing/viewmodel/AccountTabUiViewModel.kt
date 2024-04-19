package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import android.window.SplashScreen
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.screens.editprofile.EditProfileActivity
import com.cuongnl.ridehailing.screens.savedaddress.SavedAddressActivity
import com.cuongnl.ridehailing.screens.tokencheck.TokenCheckActivity

class AccountTabUiViewModel : ViewModel() {

    private var _isLanguageBottomSheetVisible = mutableStateOf(false)

    val isLanguageBottomSheetVisible : State<Boolean> = _isLanguageBottomSheetVisible

    fun setLanguageBottomSheetVisible(isVisible: Boolean) {
        _isLanguageBottomSheetVisible.value = isVisible
    }

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

    fun resetApp(context: Context) {
        val intent = Intent(context, TokenCheckActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}