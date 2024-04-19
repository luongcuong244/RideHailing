package com.cuongnl.ridehailing.screens.addingaddress.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.AddingAddressUiViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BoxScope.ConfirmButton(
    addingAddressUiViewModel: AddingAddressUiViewModel = viewModel(),
    loaderViewModel: LoaderViewModel = viewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
    ) {
        TouchableOpacityButton(
            modifier = Modifier
                .padding(horizontal = 10.sdp)
                .padding(bottom = 15.sdp)
                .fillMaxWidth()
                .shadow(
                    5.dp,
                    RoundedCornerShape(20)
                )
                .clip(RoundedCornerShape(20))
                .background(colorResource(id = R.color.orange_300))
                .padding(vertical = 11.sdp),
            onClick = {
                loaderViewModel.setLoading(true)
                addingAddressUiViewModel.onClickConfirmButton(context) {
                    loaderViewModel.setLoading(false)
                }
            }
        ) {
            AppText(
                text = stringResource(id = R.string.confirm_text),
                fontSize = 12.ssp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}