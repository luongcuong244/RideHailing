package com.cuongnl.ridehailing.screens.editprofile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.editprofile.ui.AppBar
import com.cuongnl.ridehailing.screens.editprofile.ui.ChangePasswordAndSignOut
import com.cuongnl.ridehailing.screens.editprofile.ui.ProfileContent
import com.cuongnl.ridehailing.screens.editprofile.ui.UpdateButton
import com.cuongnl.ridehailing.theme.AppTheme

class EditProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
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
            ProfileContent()
            ChangePasswordAndSignOut()
        }
        UpdateButton()
    }
}