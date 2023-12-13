package com.cuongnl.ridehailing.screens.editprofile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun Avatar() {
    Image(
        painter = painterResource(id = R.drawable.default_avatar),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 7.sdp)
            .size(75.sdp)
    )
}