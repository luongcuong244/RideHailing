package com.cuongnl.ridehailing.screens.passwordverification

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.activitybehavior.IPasswordVerificationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.extensions.showDialog
import com.cuongnl.ridehailing.screens.passwordverification.ui.ChangePhoneNumberButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.ForgotPasswordButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.HelloText
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordOtpTextField
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordPromptText
import com.cuongnl.ridehailing.screens.passwordverification.ui.WrongPasswordText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.AuthServiceViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.PasswordVerificationViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.OtpAuthType
import com.cuongnl.ridehailing.screens.otpverification.OtpVerificationActivity

val LocalActivityBehavior =
    staticCompositionLocalOf<IPasswordVerificationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class PasswordVerificationActivity : BaseActivity(), IPasswordVerificationActivityBehavior {

    private lateinit var loaderViewModel: LoaderViewModel
    private lateinit var authServiceViewModel: AuthServiceViewModel
    private lateinit var passwordVerificationViewModel: PasswordVerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    override fun popActivity() {
        finish()
    }

    override fun login() {

        val loginRequest = passwordVerificationViewModel.getLoginRequest()

        loaderViewModel.setLoading(true)
        authServiceViewModel.login(
            loginRequest,
            onFinish = {
                loaderViewModel.setLoading(false)
            },
            onWrongPassword = {
                passwordVerificationViewModel.setIsWrongPassword(true)
            },
            onSuccess = {

            }
        )
    }

    override fun showForgotPasswordDialog() {
        showDialog(
            title = getString(R.string.confirm_text),
            message = getString(R.string.forgot_password_dialog_prompt),
            textOfNegativeButton = getString(R.string.cancel_text),
            textOfPositiveButton = getString(R.string.ok_text),
            positiveButtonFunction = {
                navigateToOtpVerificationActivity()
            }
        )
    }

    private fun setupViewModel(){

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_NUMBER_PHONE)
        val countryCode = intent.getStringExtra(Constant.BUNDLE_COUNTRY_CODE)
        val internationalPhoneNumber =
            FormatterUtils.formatPhoneNumberToInternationalFormation(phoneNumber!!, countryCode!!)

        passwordVerificationViewModel = ViewModelProvider(this)[PasswordVerificationViewModel::class.java]
        passwordVerificationViewModel.setInternationalPhoneNumber(internationalPhoneNumber)

        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]
        authServiceViewModel = ViewModelProvider(this)[AuthServiceViewModel::class.java]
    }

    private fun navigateToOtpVerificationActivity(){

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_NUMBER_PHONE)
        val countryCode = intent.getStringExtra(Constant.BUNDLE_COUNTRY_CODE)

        val intent = Intent(this, OtpVerificationActivity::class.java)
        intent.putExtra(Constant.BUNDLE_OTP_AUTH_TYPE, OtpAuthType.PASSWORD_CHANGING)
        intent.putExtra(Constant.BUNDLE_NUMBER_PHONE, phoneNumber)
        intent.putExtra(Constant.BUNDLE_COUNTRY_CODE, countryCode)
        startActivity(intent)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .padding(top = 80.sdp, start = 15.sdp, end = 15.sdp)
            ) {
                HelloText()
                PasswordPromptText()
                PasswordOtpTextField()
                WrongPasswordText()

                Row(
                    modifier = Modifier
                        .padding(top = 15.sdp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ForgotPasswordButton()
                    ChangePhoneNumberButton()
                }
            }
        }
    }
}