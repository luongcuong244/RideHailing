package com.cuongnl.ridehailing.screens.otpverification.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton

@Composable
fun SendOtpButton() {
    NoRippleButton(
        onClick = {

        }
    ) {
        AppText(
            text = stringResource(id = R.string.send_otp_again)
        )
    }
}