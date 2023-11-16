package com.cuongnl.ridehailing.screens.notefordriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.notefordriver.ui.AppBar
import com.cuongnl.ridehailing.screens.notefordriver.ui.NoteTextField

class NoteForDriverActivity : BaseActivity() {
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
                .background(colorResource(id = R.color.app_background_gray))
        ) {
            AppBar()
            NoteTextField()
        }
    }
}