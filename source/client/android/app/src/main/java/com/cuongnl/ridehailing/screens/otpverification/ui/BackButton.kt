package com.cuongnl.ridehailing.screens.otpverification.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.otpverification.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BackButton() {

    val actions = LocalActivityBehavior.current

    NoRippleButton(
        onClick = {
            actions.popActivity()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 30.sdp)
                .size(25.dp),
        )
    }
}