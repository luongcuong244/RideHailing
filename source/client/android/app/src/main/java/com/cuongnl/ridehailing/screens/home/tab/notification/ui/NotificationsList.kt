package com.cuongnl.ridehailing.screens.home.tab.notification.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Notification
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun NotificationsList() {

    val data = CurrentUser.getUser()?.notifications

    LazyColumn(
        modifier = Modifier
            .padding(top = 4.sdp)
            .fillMaxSize(),
        content = {
            items(data?.size ?: 0) { index ->
                NotificationItem(
                    notification = data?.get(index) ?: return@items
                )
            }
        }
    )
}

@Composable
private fun NotificationItem(notification: Notification) {
    Row(
        modifier = Modifier
            .padding(bottom = 2.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 12.sdp, vertical = 15.sdp),
        horizontalArrangement = Arrangement.spacedBy(12.sdp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(25))
                .size(40.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = notification.date,
                    color = colorResource(id = R.color.gray_600),
                    fontSize = 10.sp,
                )

                if (!notification.isRead) {
                    Box(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .size(8.dp)
                            .background(Color.Red)
                    )
                }
            }

            AppText(
                text = notification.title,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 5.dp),
                lineHeight = 20.sp,
            )
            AppText(
                text = notification.shortContent,
                color = Color.Black,
                fontSize = 11.sp,
                lineHeight = 18.sp,
            )
        }
    }
}