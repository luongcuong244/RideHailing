package com.cuongnl.ridehailing.screens.addingaddress.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.AddingAddressUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun AppBar(
    addingAddressUiViewModel: AddingAddressUiViewModel = viewModel()
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(0.20f))
            .statusBarsPadding()
            .padding(10.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TouchableOpacityButton(
            onClick = {
                addingAddressUiViewModel.onClickBackButton(context)
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back), 
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.sdp)
                    .size(20.sdp)
            )
        }
        
        AppText(
            text = stringResource(id = R.string.add_address),
            fontSize = 12.ssp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
    }
}