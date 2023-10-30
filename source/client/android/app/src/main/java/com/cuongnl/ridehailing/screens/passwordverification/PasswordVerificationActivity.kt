package com.cuongnl.ridehailing.screens.passwordverification

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
import com.cuongnl.ridehailing.screens.passwordverification.ui.ChangePhoneNumberButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.ForgotPasswordButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.HelloText
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordOtpTextField
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordPromptText
import com.cuongnl.ridehailing.screens.passwordverification.ui.WrongPasswordText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.viewmodel.AuthServiceViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.PasswordVerificationViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp

val LocalActivityBehavior =
    staticCompositionLocalOf<IPasswordVerificationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class PasswordVerificationActivity : BaseActivity(), IPasswordVerificationActivityBehavior {

    private lateinit var loaderViewModel: LoaderViewModel
    private lateinit var authServiceViewModel: AuthServiceViewModel
    private lateinit var passwordVerificationViewModel: PasswordVerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]
        authServiceViewModel = ViewModelProvider(this)[AuthServiceViewModel::class.java]
        passwordVerificationViewModel = ViewModelProvider(this)[PasswordVerificationViewModel::class.java]

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