package com.ridehailing.driver.screens.pickupconfirmation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ridehailing.driver.R
import com.ridehailing.driver.viewmodel.PickupConfirmationUiViewModel
import com.ridehailing.driver.widgets.AppText
import com.ridehailing.driver.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DriverMovingButton(
    pickupConfirmationUiViewModel: PickupConfirmationUiViewModel = viewModel()
) {

    val text = if (pickupConfirmationUiViewModel.timeTravelInMilliseconds.value > 0) {
        stringResource(id = R.string.the_driver_will_arrive_in) + ": " + pickupConfirmationUiViewModel.timeTravelInMilliseconds.value / 1000 + "s"
    } else {
        stringResource(id = R.string.click_to_driver_move)
    }

    TouchableOpacityButton(
        onClick = {
            if (pickupConfirmationUiViewModel.timeTravelInMilliseconds.value <= 0) {
                pickupConfirmationUiViewModel.driverMoving()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 5.sdp)
                .padding(horizontal = 15.sdp, vertical = 5.sdp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20))
                .background(colorResource(id = R.color.blue_500))
                .padding(10.sdp)
        ) {
            AppText(
                text = text,
                fontSize = 11.ssp,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}