package com.cuongnl.ridehailing.screens.booking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PickupLocationMarker() {

    val markerSizeInDp = with(LocalDensity.current) {
        (Constant.MARKER_SIZE_IN_PIXEL / density).dp
    }

    Box(
        modifier = Modifier
            .padding(bottom = markerSizeInDp)
            .padding(bottom = 5.sdp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(15),
            )
            .width(120.sdp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(15))
            .background(Color.White)
    ) {
        AppText(
            modifier = Modifier
                .padding(5.sdp),
            text = "Helldsdsdsdsddsdsdsdsddsdssddsdsdsdsds",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
    }
}