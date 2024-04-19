package com.cuongnl.ridehailing.screens.savedaddress.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.SavedAddressUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun OtherPlaces() {
    val data = CurrentUser.getUser()?.getOtherAddresses()

    val size = if (data != null) {
        data.size + 1
    } else {
        1
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        content = {
            items(size) { index ->
                if (data == null) {
                    AddAddressItem()
                } else {
                    if (index < data.size) {
                        AddressItem(data[index])
                    } else {
                        AddAddressItem()
                    }
                }
            }
        },
    )
}

@Composable
private fun AddressItem(
    item: Address
) {
    NoRippleButton(
        onClick = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.sdp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 15.sdp),
                horizontalArrangement = Arrangement.spacedBy(10.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(25.sdp),
                    painter = painterResource(id = R.drawable.app_common_assets_icons_iconplacecircle_iconplacecircle),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    AppText(
                        text = FormatterUtils.getShortAddress(item.fullName, 1),
                        color = Color.Black,
                        fontSize = 11.ssp,
                        fontWeight = FontWeight.Medium
                    )
                    AppText(
                        text = item.fullName,
                        color = Color.Gray,
                        fontSize = 10.ssp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.sdp)
                .background(colorResource(id = R.color.gray_200)))
        }
    }
}

@Composable
private fun AddAddressItem(
    savedAddressUiViewModel: SavedAddressUiViewModel = viewModel()
) {

    val context = LocalContext.current

    NoRippleButton(
        onClick = {
            savedAddressUiViewModel.navigateToAddAddressScreen(context, AddressType.OTHER)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.sdp)
                .padding(horizontal = 15.sdp),
            horizontalArrangement = Arrangement.spacedBy(10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(25.sdp),
                painter = painterResource(id = R.drawable.app_common_assets_icons_iconaddlocation_iconaddlocation),
                contentDescription = null
            )
            AppText(
                text = stringResource(id = R.string.add_address),
                color = Color.Black,
                fontSize = 11.ssp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}