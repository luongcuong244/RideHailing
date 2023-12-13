package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.screens.changepassword.ChangePasswordActivity
import com.cuongnl.ridehailing.screens.login.LoginScreen
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.LocalStorageUtils

class EditProfileUiViewModel : ViewModel() {

    private val _nameTextField = mutableStateOf(TextFieldValue(CurrentUser.getUser()?.userName?.value ?: ""))
    private val _emailTextField = mutableStateOf(TextFieldValue(CurrentUser.getUser()?.email?.value ?: ""))

    fun getNameTextFieldValue(): MutableState<TextFieldValue> {
        return _nameTextField
    }

    fun setNameTextField(text: String) {
        _nameTextField.value = _nameTextField.value.copy(text = text)
    }

    fun getEmailTextFieldValue(): MutableState<TextFieldValue> {
        return _emailTextField
    }

    fun setEmailTextField(text: String) {
        _emailTextField.value = _emailTextField.value.copy(text = text)
    }

    fun onClickBackButton(context: Context) {
        context.findActivity()?.onBackPressed()
    }

    fun onClickUpdateButton(context: Context) {

    }

    fun onClickChangePasswordButton(context: Context) {
        val intent = Intent(context, ChangePasswordActivity::class.java)
        intent.putExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER, CurrentUser.getUser()?.phoneNumber?.value)
        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun onClickSignOutButton(context: Context) {
        CurrentUser.setUser(null)
        LocalStorageUtils.writeData(context, LocalStorageUtils.KEY_ACCESS_TOKEN, "")

        val intent = Intent(context, LoginScreen::class.java)
        context.startActivity(intent)
        context.findActivity()?.finishAffinity()
    }
}