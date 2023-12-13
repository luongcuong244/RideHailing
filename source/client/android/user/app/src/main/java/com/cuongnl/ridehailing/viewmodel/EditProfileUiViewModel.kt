package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.globalstate.CurrentUser

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
}