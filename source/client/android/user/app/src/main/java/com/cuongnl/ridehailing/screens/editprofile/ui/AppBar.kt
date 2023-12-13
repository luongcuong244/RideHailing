package com.cuongnl.ridehailing.screens.editprofile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .statusBarsPadding()
            .height(45.sdp)
            .padding(horizontal = 15.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TouchableOpacityButton(
            onClick = {

            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(17.sdp)
            )
        }

        AppText(
            text = stringResource(R.string.edit_profile),
            fontSize = 12.ssp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 10.sdp)
        )
    }
}