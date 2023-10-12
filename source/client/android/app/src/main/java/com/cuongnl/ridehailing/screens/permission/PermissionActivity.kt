package com.cuongnl.ridehailing.screens.permission

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cuongnl.ridehailing.core.BaseActivity

class PermissionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen(){
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {

        }
    }
}