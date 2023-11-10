package com.cuongnl.ridehailing.screens.confirmdestinationlocation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import ir.kaaveh.sdpcompose.sdp
import com.cuongnl.ridehailing.R

@Composable
fun BoxScope.BottomView() {
    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(topStart = 15.sdp, topEnd = 15.sdp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.sdp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.sdp))
                .background(colorResource(id = R.color.gray_200))
                .padding(10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            LocationIcon()
            AddressView()
        }
    }
}