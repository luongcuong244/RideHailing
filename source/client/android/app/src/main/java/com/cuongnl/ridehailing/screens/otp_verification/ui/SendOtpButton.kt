package com.cuongnl.ridehailing.screens.otp_verification.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import com.cuongnl.ridehailing.R

@Composable
fun SendOtpButton(){
    NoRippleButton(
        onClick = {

        }
    ) {
        AppText(
            text = stringResource(id = R.string.send_otp_again)
        )
    }
}