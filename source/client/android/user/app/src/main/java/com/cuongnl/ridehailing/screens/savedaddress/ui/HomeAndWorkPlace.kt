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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.viewmodel.SavedAddressUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HomeAndWorkPlace(
    savedAddressUiViewModel: SavedAddressUiViewModel = viewModel()
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp)
            .background(Color.White)
            .padding(horizontal = 5.sdp)
    ) {

        Item(
            title = stringResource(id = R.string.home),
            addressString = CurrentUser.getUser()?.getHomeAddress()?.fullName,
            icon = painterResource(id = R.drawable.ic_homeplace)
        ) {
            if (CurrentUser.getUser()?.getHomeAddress() == null) {
                savedAddressUiViewModel.navigateToAddAddressScreen(
                    context = context,
                    addressType = AddressType.HOME
                )
            }
        }
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.sdp)
                .background(colorResource(id = R.color.gray_200))
        )
        
        Item(
            title = stringResource(id = R.string.work),
            addressString = CurrentUser.getUser()?.getWorkAddress()?.fullName,
            icon = painterResource(id = R.drawable.ic_workplace)
        ) {
            if (CurrentUser.getUser()?.getWorkAddress() == null) {
                savedAddressUiViewModel.navigateToAddAddressScreen(
                    context = context,
                    addressType = AddressType.WORK
                )
            }
        }
    }
}

@Composable
private fun Item(title: String, addressString: String?, icon: Painter, onClick: () -> Unit) {
    TouchableOpacityButton(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.sdp)
                .padding(horizontal = 10.sdp),
            horizontalArrangement = Arrangement.spacedBy(10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(25.sdp),
                painter = icon,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                AppText(
                    text = title,
                    color = Color.Black,
                    fontSize = 11.ssp,
                    fontWeight = FontWeight.Medium
                )
                if (addressString != null) {
                    AppText(
                        text = addressString,
                        color = Color.Gray,
                        fontSize = 10.ssp
                    )
                }
            }
        }
    }
}