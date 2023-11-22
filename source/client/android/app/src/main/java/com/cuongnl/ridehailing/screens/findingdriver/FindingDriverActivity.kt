package com.cuongnl.ridehailing.screens.findingdriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.theme.AppTheme

class FindingDriverActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {
    AppTheme {

    }
}