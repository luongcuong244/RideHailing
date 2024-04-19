package com.cuongnl.ridehailing.screens.addingaddress.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.AddingAddressUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BoxScope.AddressText(
    addingAddressUiViewModel: AddingAddressUiViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 15.sdp)
            .padding(horizontal = 10.sdp)
            .shadow(
                3.dp,
                RoundedCornerShape(20)
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
            .background(Color.White)
            .padding(vertical = 10.sdp, horizontal = 10.sdp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icons_ico_map_pin),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.sdp)
                .size(20.sdp)
        )

        AppText(
            text = addingAddressUiViewModel.addressName.value,
            fontWeight = FontWeight.Bold,
            fontSize = 14.ssp,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}