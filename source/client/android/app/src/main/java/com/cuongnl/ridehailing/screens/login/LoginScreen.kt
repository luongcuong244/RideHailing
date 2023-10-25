package com.cuongnl.ridehailing.screens.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activity_behavior.ILoginActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.login.ui.BannerImage
import com.cuongnl.ridehailing.screens.login.ui.ContinueButton
import com.cuongnl.ridehailing.screens.login.ui.NumberPhoneInvalidText
import com.cuongnl.ridehailing.screens.login.ui.PhoneCodeBottomSheet
import com.cuongnl.ridehailing.screens.login.ui.PhoneEditText
import com.cuongnl.ridehailing.screens.login.ui.PolicyText
import com.cuongnl.ridehailing.screens.login.ui.TitleText
import com.cuongnl.ridehailing.screens.otp_verification.OtpVerificationActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.AuthServiceViewModel
import com.cuongnl.ridehailing.viewmodel.CountryCodeSelectedViewModel
import com.cuongnl.ridehailing.viewmodel.TextEnteredViewModel
import ir.kaaveh.sdpcompose.sdp


val LocalActivityBehavior = staticCompositionLocalOf<ILoginActivityBehavior> { error("No LocalActivityActionsClass provided") }

class LoginScreen : BaseActivity(), ILoginActivityBehavior {

    private lateinit var authServiceViewModel: AuthServiceViewModel
    private lateinit var textEnteredViewModel: TextEnteredViewModel
    private lateinit var countryCodeSelectedViewModel: CountryCodeSelectedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createViewModels()

        setContent {
            CompositionLocalProvider(LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    private fun createViewModels(){
        authServiceViewModel = ViewModelProvider(this)[AuthServiceViewModel::class.java]
        textEnteredViewModel = ViewModelProvider(this)[TextEnteredViewModel::class.java]
        countryCodeSelectedViewModel = ViewModelProvider(this)[CountryCodeSelectedViewModel::class.java]
    }

    override fun isInvalidTextVisible(): Boolean {
        val textEnteredLength = textEnteredViewModel.text.value.length

        return !canClickContinueButton() && textEnteredLength != 0
    }

    override fun canClickContinueButton(): Boolean {
        val textEnteredLength = textEnteredViewModel.text.value.length
        val maxLength = countryCodeSelectedViewModel.currentCountryCode.value.maxLength
        val minLength = countryCodeSelectedViewModel.currentCountryCode.value.minLength

        return textEnteredLength in minLength..maxLength
    }

    override fun openPolicy() {
        val uri = Uri.parse(Constant.POLICY_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onContinueButtonClicked() {
        if(canClickContinueButton()){

            val numberPhone = textEnteredViewModel.text.value

            authServiceViewModel.checkExistingUser(
                numberPhone,
                onUserExisting = {

                },
                onUserNotExisting = {
                    val intent = Intent(this, OtpVerificationActivity::class.java)
                    intent.putExtra(Constant.BUNDLE_NUMBER_PHONE, numberPhone)
                    startActivity(intent)
                },
                onError = {
                    Toast.makeText(this, getString(R.string.cannot_connect_to_server), Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
private fun Screen(){
    AppTheme {
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

                PhoneCodeBottomSheet()
            }
        }
    }
}