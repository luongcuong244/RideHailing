package com.cuongnl.ridehailing.screens.home.tab.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.viewmodel.AccountTabUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun Header(
    accountTabUiViewModel: AccountTabUiViewModel = viewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_voucher_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.sdp),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 15.sdp)
                .shadow(
                    elevation = 5.sdp,
                    shape = RoundedCornerShape(15)
                )
                .fillMaxWidth()
                .size(60.sdp)
                .clip(RoundedCornerShape(15))
                .background(Color.White)
                .padding(horizontal = 8.sdp, vertical = 12.sdp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons_avatar_user),
                contentDescription = null,
                modifier = Modifier
                    .size(40.sdp)
                    .align(Alignment.CenterVertically),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.sdp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = CurrentUser.getUser()?.userName?.value!!,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 14.ssp,
                    modifier = Modifier
                        .offset(y = (-4).sdp)
                )
                AppText(
                    text = CurrentUser.getUser()?.phoneNumber?.value!!,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 10.ssp,
                )
            }
            TouchableOpacityButton(
                onClick = {
                    accountTabUiViewModel.navigateToEditProfile(context)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 7.sdp)
                        .height(13.sdp)
                )
            }
        }
    }
}