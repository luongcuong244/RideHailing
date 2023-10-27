package com.cuongnl.ridehailing.screens.otp_verification

import OtpTextField
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.otp_verification.ui.OtpDescriptionText
import com.cuongnl.ridehailing.screens.otp_verification.ui.OtpVerificationText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.viewmodel.OtpVerificationViewModel
import ir.kaaveh.sdpcompose.sdp

class OtpVerificationActivity : BaseActivity() {
    
    private lateinit var otpVerificationViewModel : OtpVerificationViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }

        otpVerificationViewModel = ViewModelProvider(this)[OtpVerificationViewModel::class.java]

        initiateOtp()
    }

    private fun initiateOtp() {
        otpVerificationViewModel.initiateOtp(this, "+84 972 085 801")
    }
    
    override fun onBackPressed() {}
}

@Composable
private fun Screen(otpVerificationViewModel: OtpVerificationViewModel = viewModel()) {
    AppTheme {
        Column(
            modifier = Modifier
                .padding(top = 80.sdp, start = 15.sdp, end = 15.sdp)
        ) {
            OtpVerificationText()
            OtpDescriptionText()
            OtpTextField(
                otpText = "",
                onOtpTextChange = { otp, otpInputFilled ->
                    if(otpInputFilled) {
                        otpVerificationViewModel.verifyOtp(otp)
                    }
                }
            )
        }
    }
}