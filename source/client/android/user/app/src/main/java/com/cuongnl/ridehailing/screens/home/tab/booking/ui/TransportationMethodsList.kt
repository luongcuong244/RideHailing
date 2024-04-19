package com.cuongnl.ridehailing.screens.home.tab.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.viewmodel.BookingTabUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun TransportationMethodsList() {
    
    LazyRow(
        modifier = Modifier
            .padding(top = 10.sdp, bottom = 12.sdp),
        content = {
            items(TransportationType.values().size) {
                AddItem(it)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(19.sdp, Alignment.CenterHorizontally),
    )
}

@Composable
private fun AddItem(
    index: Int,
    bookingTabUiViewModel: BookingTabUiViewModel = viewModel()
) {
    val context = LocalContext.current
    val item = TransportationType.values()[index]
    
    NoRippleButton(
        onClick = {
            bookingTabUiViewModel.onClickVehicleItem(context, item)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.sdp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .background(Color.White)
                    .size(55.sdp)
                    .padding(top = 5.sdp, start = 5.sdp, end = 5.sdp)
            )

            AppText(
                text = stringResource(id = item.text),
                fontSize = 8.ssp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}