package com.cuongnl.ridehailing.screens.selectinglocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        BackButton()
        Title()
    }
}

@Composable
private fun BackButton() {
    NoRippleButton {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .size(18.sdp)
        )
    }
}

@Composable
private fun Title() {
    AppText(
        text = stringResource(id = R.string.select_destination),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Black,
    )
}