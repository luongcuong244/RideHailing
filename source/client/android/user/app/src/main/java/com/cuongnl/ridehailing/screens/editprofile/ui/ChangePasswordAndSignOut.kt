package com.cuongnl.ridehailing.screens.editprofile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.kaaveh.sdpcompose.sdp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ChangePasswordAndSignOut() {
    Column(
        modifier = Modifier
            .padding(top = 15.sdp)
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChangePassword()
        Divider()
        SignOut()
    }
}

@Composable
private fun ChangePassword() {
    NoRippleButton(
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .padding(10.sdp)
                .padding(horizontal = 5.sdp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.images_password_password),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 7.sdp)
                    .size(18.sdp)
            )
            AppText(
                text = stringResource(id = R.string.change_password),
                fontWeight = FontWeight.Medium,
                fontSize = 11.ssp,
                color = Color.Black,
            )
        }
    }
}

@Composable
private fun Divider() {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.sdp)
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(id = R.color.gray_300))
    )
}

@Composable
private fun SignOut() {
    NoRippleButton(
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .padding(10.sdp)
                .padding(horizontal = 5.sdp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.images_signout_signout),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 7.sdp)
                    .size(18.sdp)
            )
            AppText(
                text = stringResource(id = R.string.sign_out),
                fontWeight = FontWeight.Medium,
                fontSize = 11.ssp,
                color = Color.Black,
            )
        }
    }
}