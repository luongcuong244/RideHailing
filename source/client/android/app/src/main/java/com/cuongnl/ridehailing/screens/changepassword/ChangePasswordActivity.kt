package com.cuongnl.ridehailing.screens.changepassword

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.activitybehavior.IChangePasswordActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.changepassword.ui.ContinueButton
import com.cuongnl.ridehailing.screens.changepassword.ui.PasswordTextField
import com.cuongnl.ridehailing.screens.changepassword.ui.PasswordVisibilityButton
import com.cuongnl.ridehailing.screens.changepassword.ui.ResetPasswordText
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.widgets.BackButton
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp

val LocalActivityBehavior =
    staticCompositionLocalOf<IChangePasswordActivityBehavior> { error("No LocalActivityActionsClass provided") }

class ChangePasswordActivity : BaseActivity(), IChangePasswordActivityBehavior {
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

    override fun changePassword() {
        finish()
    }
}

@Composable
private fun Screen() {

    val actions = LocalActivityBehavior.current

    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.sdp)
                    .fillMaxSize()
            ) {
                BackButton {
                    actions.popActivity()
                }
                ResetPasswordText()
                PasswordTextField()
                PasswordVisibilityButton()

                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ContinueButton()
                }
            }
        }
    }
}