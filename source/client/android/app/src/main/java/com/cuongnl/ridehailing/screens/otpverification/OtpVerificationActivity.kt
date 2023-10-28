package com.cuongnl.ridehailing.screens.otpverification

import OtpTextField
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.IOtpVerificationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.OtpAuthType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.screens.otpverification.ui.BackButton
import com.cuongnl.ridehailing.screens.otpverification.ui.OtpDescriptionText
import com.cuongnl.ridehailing.screens.otpverification.ui.OtpTimeout
import com.cuongnl.ridehailing.screens.otpverification.ui.OtpVerificationText
import com.cuongnl.ridehailing.screens.otpverification.ui.SendOtpButton
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.OtpVerificationViewModel
import com.cuongnl.ridehailing.widgets.SimpleAlertDialog
import ir.kaaveh.sdpcompose.sdp

val LocalActivityBehavior =
    staticCompositionLocalOf<IOtpVerificationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class OtpVerificationActivity : BaseActivity(), IOtpVerificationActivityBehavior {

    private lateinit var otpVerificationViewModel: OtpVerificationViewModel

    private var otpAuthType: OtpAuthType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }

        otpAuthType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                Constant.BUNDLE_OTP_AUTH_TYPE,
                OtpAuthType::class.java
            )
        } else {
            intent.getSerializableExtra(Constant.BUNDLE_OTP_AUTH_TYPE) as OtpAuthType?
        }

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_NUMBER_PHONE)
        val countryCode = intent.getStringExtra(Constant.BUNDLE_COUNTRY_CODE)
        val internationalPhoneNumber =
            FormatterUtils.formatPhoneNumberToInternationalFormation(phoneNumber!!, countryCode!!)

        otpVerificationViewModel = ViewModelProvider(this)[OtpVerificationViewModel::class.java]
        otpVerificationViewModel.setInternationalPhoneNumber(internationalPhoneNumber)

        initiateOtp()
    }

    override fun initiateOtp() {
        otpVerificationViewModel.initiateOtp(this)
    }

    override fun popActivity() {
        finish()
    }

    override fun navigateToNextActivity() {
        if (otpAuthType != null){
            when(otpAuthType) {
                OtpAuthType.SIGN_UP -> {

                }
                OtpAuthType.PASSWORD_CHANGING -> {

                }
                else -> {
                    throw Exception("The provided otp auth type is not match with any type")
                }
            }
        } else {
            throw Exception("Must provide otp auth type")
        }
    }
}

@Composable
private fun Screen(otpVerificationViewModel: OtpVerificationViewModel = viewModel()) {

    val actions = LocalActivityBehavior.current
    val context = LocalContext.current

    AppTheme {
        Column(
            modifier = Modifier
                .padding(top = 30.sdp, start = 15.sdp, end = 15.sdp),
        ) {
            BackButton()
            OtpVerificationText()
            OtpDescriptionText()
            OtpTextField(
                otpText = "",
                onOtpTextChange = { otp, otpInputFilled ->
                    if (otpInputFilled) {
                        val activity = context.findActivity()
                        activity?.let {
                            if (activity is OtpVerificationActivity) {
                                otpVerificationViewModel.verifyOtp(activity, otp)
                            } else {
                                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
            )
            SendOtpButton()
            OtpTimeout()

            if (otpVerificationViewModel.errorOccurred.value != null) {
                SimpleAlertDialog(
                    dialogTitle = stringResource(id = R.string.an_error_occurred),
                    dialogText = otpVerificationViewModel.errorOccurred.value!!.message!!,
                    showDismissButton = false,
                    onConfirmation = {
                        actions.popActivity()
                    }
                )
            }
        }
    }
}