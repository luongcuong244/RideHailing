package com.cuongnl.ridehailing.screens.home.tab.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import ir.kaaveh.sdpcompose.sdp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.ssp

@Composable
fun AddressesList() {

    val data = CurrentUser.getUser()?.addresses

    var isLoading = true

    if (data != null) {
        isLoading = false
    }

    LazyRow(
        modifier = Modifier
            .padding(vertical = 12.sdp),
        content = {
            items(data?.size!! + 1) { index ->
                if (isLoading) {
                    ItemContent(isLoading = false)
                } else {
                    if (index < data.size) {
                        AddressItem(data[index])
                    } else {
                        AddAddressItem()
                    }
                }
            }
        },
        horizontalArrangement = Arrangement.spacedBy(8.sdp, Alignment.CenterHorizontally),
    )
}

@Composable
private fun AddressItem(item: Address) {

    val icon = when (item.addressType) {
        AddressType.HOME -> painterResource(id = R.drawable.ic_homeplace)
        AddressType.WORK -> painterResource(id = R.drawable.ic_workplace)
        else -> painterResource(id = R.drawable.ic_placeadd)
    }

    val title = when (item.addressType) {
        AddressType.HOME -> stringResource(id = R.string.home_text)
        AddressType.WORK -> stringResource(id = R.string.company_text)
        else -> item.fullName
    }

    val description = when (item.addressType) {
        AddressType.HOME -> item.fullName
        AddressType.WORK -> item.fullName
        else -> null
    }

    ItemContent(icon, title, description) {

    }
}

@Composable
private fun AddAddressItem() {

    val icon = painterResource(id = R.drawable.ic_placeadd)

    val title = "Thêm địa chỉ"

    ItemContent(icon, title, null) {

    }
}

@Composable
private fun ItemContent(
    icon: Painter,
    title: String,
    description: String?,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {

    NoRippleButton(
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .width(119.sdp)
                .height(32.sdp)
                .clip(RoundedCornerShape(30))
                .background(Color.White)
                .padding(horizontal = 5.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.sdp)
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 5.sdp)
                    .size(20.sdp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                AppText(
                    text = title,
                    color = Color.Black,
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (description != null) {
                    AppText(
                        text = description,
                        color = Color.Black,
                        fontSize = 8.ssp,
                        fontWeight = FontWeight.Light,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 0.1.sp
                    )
                }
            }
        }
    }
}