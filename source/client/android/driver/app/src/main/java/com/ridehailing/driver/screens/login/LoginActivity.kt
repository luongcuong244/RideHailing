package com.ridehailing.driver.screens.login

import android.os.Bundle
import androidx.activity.compose.setContent
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.theme.AppTheme

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {

            }
        }
    }
}