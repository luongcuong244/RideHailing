package com.cuongnl.ridehailing.screens.otp_verification

import OtpTextField
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.otp_verification.ui.OtpDescriptionText
import com.cuongnl.ridehailing.screens.otp_verification.ui.OtpVerificationText
import com.cuongnl.ridehailing.theme.AppTheme
import ir.kaaveh.sdpcompose.sdp

class OtpVerificationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }

    override fun onBackPressed() {}
}

@Composable
private fun Screen(){
    AppTheme {
        Column(
            modifier = Modifier
                .padding(top = 80.sdp, start = 15.sdp, end = 15.sdp)
        ) {
            OtpVerificationText()
            OtpDescriptionText()
            OtpTextField(otpText = "", onOtpTextChange = { _, _ ->})
        }
    }
}