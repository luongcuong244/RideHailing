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
import com.cuongnl.ridehailing.activitybehavior.IPasswordVerificationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.passwordverification.ui.ChangePhoneNumberButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.ForgotPasswordButton
import com.cuongnl.ridehailing.screens.passwordverification.ui.HelloText
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordOtpTextField
import com.cuongnl.ridehailing.screens.passwordverification.ui.PasswordPromptText
import com.cuongnl.ridehailing.screens.passwordverification.ui.WrongPasswordText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp

val LocalActivityBehavior =
    staticCompositionLocalOf<IPasswordVerificationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class PasswordVerificationActivity : BaseActivity(), IPasswordVerificationActivityBehavior {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    override fun popActivity() {
        finish()
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