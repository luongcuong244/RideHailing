package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.custom_view.AppText
import com.cuongnl.ridehailing.viewmodel.NumberPhoneSelectedViewModel
import com.cuongnl.ridehailing.viewmodel.TextEnteredViewModel

import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun NumberPhoneInvalidText(
    textEnteredViewModel: TextEnteredViewModel = viewModel(),
    phoneSelectedViewModel: NumberPhoneSelectedViewModel = viewModel()
){

    val textEnteredLength = textEnteredViewModel.text.value.length
    val maxLength = phoneSelectedViewModel.currentNumberPhone.value.maxLength

    val isInvalidTextVisible = textEnteredLength <= maxLength

    if(isInvalidTextVisible){
        AppText(
            modifier = Modifier
                .padding(bottom = 5.sdp),
            text = stringResource(id = R.string.number_phone_invalid),
            color = Color.Red,
            fontSize = 10.ssp,
        )
    }
}