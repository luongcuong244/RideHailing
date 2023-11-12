package com.cuongnl.ridehailing.screens.selectinglocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun FetchAddressResponses(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(bottomEnd = 10.sdp, bottomStart = 10.sdp))
            .background(Color.White)
            .padding(top = 20.sdp)
            .padding(horizontal = 10.sdp),
        content = {
            items(5) {
                AddItem(it)
            }
        },
    )
}

@Composable
private fun AddItem(index: Int) {

    Column {
        Row(
            modifier = Modifier
                .padding(7.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(18.sdp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                AppText(
                    text = "199 Phố Liễu Giai",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )

                AppText(
                    text = "199 Phố Liễu Giai, Liễu Giai, Ba Đình, Hà Nội, Việt Nam",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = colorResource(id = R.color.gray_700)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(colorResource(id = R.color.gray_200))
        )
    }
}