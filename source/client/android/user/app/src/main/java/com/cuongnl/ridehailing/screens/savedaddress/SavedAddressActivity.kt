package com.cuongnl.ridehailing.screens.savedaddress

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.savedaddress.ui.AppBar
import com.cuongnl.ridehailing.screens.savedaddress.ui.HomeAndWorkPlace
import com.cuongnl.ridehailing.screens.savedaddress.ui.OtherPlaces
import com.cuongnl.ridehailing.theme.AppTheme

class SavedAddressActivity : BaseActivity() {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(0.30f))
                .imePadding()
        ) {
            AppBar()
            HomeAndWorkPlace()
            OtherPlaces()
        }
    }
}