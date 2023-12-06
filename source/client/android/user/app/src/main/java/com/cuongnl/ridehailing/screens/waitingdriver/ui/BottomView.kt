package com.cuongnl.ridehailing.screens.waitingdriver.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.kaaveh.sdpcompose.sdp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.WaitingDriverUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BottomView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.gray_200)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeUntilDriverArrives()
        DriverInfo()
        ContactCustomer()
    }
}

@Composable
private fun TimeUntilDriverArrives(
    waitingDriverUiViewModel: WaitingDriverUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.sdp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 15.sdp, vertical = 7.sdp),
        verticalArrangement = Arrangement.spacedBy(3.sdp),
    ) {
        AppText(
            text = "Time until driver arrives",
            color = Color.Black,
            fontSize = 12.ssp,
            fontWeight = FontWeight.Bold
        )
        AppText(
            text =  + ,
            color = colorResource(id = R.color.gray_600),
            fontSize = 10.ssp,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun DriverInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 15.sdp, vertical = 7.sdp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.sdp),
        ) {
            DriverName()
            VehicleBrand()
            LicensePlate()
        }
        DriverAvatarAndRating()
    }
}

@Composable
private fun DriverName() {
    AppText(
        text = "Nguyễn Lương Cường",
        color = Color.Black,
        fontSize = 12.ssp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(bottom = 2.sdp)
    )
}

@Composable
private fun VehicleBrand() {
    AppText(
        text = "Honda Civic",
        color = colorResource(id = R.color.gray_800),
        fontSize = 10.ssp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(bottom = 8.sdp)
    )
}

@Composable
private fun LicensePlate() {
    AppText(
        modifier = Modifier
            .background(colorResource(id = R.color.gray_300))
            .padding(horizontal = 5.sdp, vertical = 2.sdp),
        text = "29V5-48027",
        color = Color.Black,
        fontSize = 12.ssp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun DriverAvatarAndRating() {
    Box(
        modifier = Modifier
            .width(55.sdp)
            .height(59.sdp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .shadow(2.dp)
                .background(Color.White)
                .padding(horizontal = 4.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.sdp)
        ) {
            AppText(
                text = "4.9",
                fontSize = 10.ssp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 1.sdp)
                    .size(8.sdp)
            )
        }
    }
}

@Composable
private fun ContactCustomer(
    waitingDriverUiViewModel: WaitingDriverUiViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 20.sdp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TouchableOpacityButton(
            onClick = {

            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_chatting),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.sdp)
                        .size(13.sdp)
                )
                AppText(
                    text = stringResource(id = R.string.texting),
                    fontSize = 11.ssp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        TouchableOpacityButton(
            onClick = {

            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone_call),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.sdp)
                        .size(13.sdp)
                )
                AppText(
                    text = stringResource(id = R.string.calling),
                    fontSize = 11.ssp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}