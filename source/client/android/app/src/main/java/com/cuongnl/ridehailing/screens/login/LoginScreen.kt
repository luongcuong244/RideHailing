package com.cuongnl.ridehailing.screens.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cuongnl.ridehailing.activity_behavior.ILoginActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.login.ui.BannerImage
import com.cuongnl.ridehailing.screens.login.ui.ContinueButton
import com.cuongnl.ridehailing.screens.login.ui.NumberPhoneInvalidText
import com.cuongnl.ridehailing.screens.login.ui.PhoneEditText
import com.cuongnl.ridehailing.screens.login.ui.PolicyText
import com.cuongnl.ridehailing.screens.login.ui.TitleText
import com.cuongnl.ridehailing.utils.Constant
import ir.kaaveh.sdpcompose.sdp


val LocalActivityBehavior = staticCompositionLocalOf<ILoginActivityBehavior> { error("No LocalActivityActionsClass provided") }

class LoginScreen : BaseActivity(), ILoginActivityBehavior {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    override fun openPolicy() {
        val uri = Uri.parse(Constant.POLICY_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onContinueButtonClicked() {

    }
}

@Composable
private fun Screen(){
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                BannerImage()
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.sdp)
                        .imePadding()
                ) {
                    TitleText()
                    PhoneEditText()
                    NumberPhoneInvalidText()

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        ContinueButton()
                        PolicyText()
                    }
                }
            }
        }
    }
}