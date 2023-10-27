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
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.OtpVerificationViewModel
import ir.kaaveh.sdpcompose.sdp

class OtpVerificationActivity : BaseActivity() {
    
    private lateinit var otpVerificationViewModel : OtpVerificationViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_NUMBER_PHONE)
        val countryCode = intent.getStringExtra(Constant.BUNDLE_COUNTRY_CODE)
        val internationalPhoneNumber = FormatterUtils.formatPhoneNumberToInternationalFormation(phoneNumber!!, countryCode!!)

        otpVerificationViewModel = ViewModelProvider(this)[OtpVerificationViewModel::class.java]
        otpVerificationViewModel.setInternationalPhoneNumber(internationalPhoneNumber)

        initiateOtp()
    }

    private fun initiateOtp() {
        otpVerificationViewModel.initiateOtp(this)
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