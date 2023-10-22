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
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.activity_behavior.ILoginActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.login.ui.BannerImage
import com.cuongnl.ridehailing.screens.login.ui.ContinueButton
import com.cuongnl.ridehailing.screens.login.ui.NumberPhoneInvalidText
import com.cuongnl.ridehailing.screens.login.ui.PhoneEditText
import com.cuongnl.ridehailing.screens.login.ui.PolicyText
import com.cuongnl.ridehailing.screens.login.ui.TitleText
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.NumberPhoneSelectedViewModel
import com.cuongnl.ridehailing.viewmodel.TextEnteredViewModel
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

    override fun isInvalidTextVisible(): Boolean {
        val textEnteredViewModel = ViewModelProvider(this)[TextEnteredViewModel::class.java]
        val textEnteredLength = textEnteredViewModel.text.value.length

        return !canClickContinueButton() && textEnteredLength != 0
    }

    override fun canClickContinueButton(): Boolean {
        val textEnteredViewModel = ViewModelProvider(this)[TextEnteredViewModel::class.java]
        val phoneSelectedViewModel = ViewModelProvider(this)[NumberPhoneSelectedViewModel::class.java]

        val textEnteredLength = textEnteredViewModel.text.value.length
        val maxLength = phoneSelectedViewModel.currentNumberPhone.value.maxLength
        val minLength = phoneSelectedViewModel.currentNumberPhone.value.minLength

        return textEnteredLength in minLength..maxLength
    }

    override fun openPolicy() {
        val uri = Uri.parse(Constant.POLICY_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onContinueButtonClicked() {
        if(canClickContinueButton()){

        }
    }
}

@Composable
private fun Screen(){
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
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