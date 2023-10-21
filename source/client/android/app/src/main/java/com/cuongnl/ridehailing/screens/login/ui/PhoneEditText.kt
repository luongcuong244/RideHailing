package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PhoneEditText(){
    Row(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .height(40.sdp)
            .border(
                1.sdp,
                colorResource(id = R.color.app_color),
                RoundedCornerShape(8.sdp)
            )
    ) {
        PhoneCode()
    }
}

@Composable
private fun PhoneCode(){
    Row(
        modifier = Modifier
            .fillMaxHeight()
    ) {

    }
}