package com.cuongnl.ridehailing.screens.home.tab.history.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.teal_700).copy(alpha = 0.8f))
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_header_history),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        AppText(
            text = stringResource(id = R.string.we_know_you_have_many_options),
            fontSize = 10.ssp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.sdp),
        )
    }
}