package com.cuongnl.ridehailing.screens.passwordverification.ui

import OtpTextField
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.FullScreenLoaderViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PasswordOtpTextField(fullScreenLoaderViewModel: FullScreenLoaderViewModel = viewModel()) {
    OtpTextField(
        onOtpTextChange = { otp, otpInputFilled ->
            if (otpInputFilled) {
                fullScreenLoaderViewModel.setLoading(true)
            }
        },
        isTextVisible = false,
        modifier = Modifier
            .padding(horizontal = 7.sdp)
    )
}