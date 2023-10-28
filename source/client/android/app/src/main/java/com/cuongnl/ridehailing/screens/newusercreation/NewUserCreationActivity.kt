package com.cuongnl.ridehailing.screens.newusercreation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.activitybehavior.INewUserCreationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordCreationBottomSheet
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserEmailTextField
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserInfoCreationDescription
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserInfoCreationTitle
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserNameContinueButton
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserNameTextField
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.widgets.BackButton
import ir.kaaveh.sdpcompose.sdp

val LocalActivityBehavior =
    staticCompositionLocalOf<INewUserCreationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class NewUserCreationActivity : BaseActivity(), INewUserCreationActivityBehavior {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalActivityBehavior provides this) {
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

    val actions = LocalActivityBehavior.current

    AppTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.sdp),
        ) {
            BackButton {
                actions.popActivity()
            }
            UserInfoCreationTitle()
            UserInfoCreationDescription()
            UserNameTextField()
            UserEmailTextField()
            UserNameContinueButton()

            PasswordCreationBottomSheet()
        }
    }
}