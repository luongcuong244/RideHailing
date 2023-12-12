package com.cuongnl.ridehailing.screens.home.tab.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun Body() {
    Column(
        modifier = Modifier
            .padding(top = 10.sdp)
            .padding(horizontal = 15.sdp)
            .shadow(
                elevation = 1.sdp,
                shape = RoundedCornerShape(5)
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(5))
            .background(Color.White)
            .padding(horizontal = 12.sdp)
    ) {
        Item(
            icon = painterResource(id = R.drawable.icons_ico_membershipclass),
            title = stringResource(id = R.string.membership_class),
            onClick = {

            }
        )
        Item(
            icon = painterResource(id = R.drawable.icon_images_invite_invite),
            title = stringResource(id = R.string.invite_friends),
            onClick = {

            }
        )
        Item(
            icon = painterResource(id = R.drawable.icons_iconpayment_iconpayment),
            title = stringResource(id = R.string.payment),
            onClick = {

            }
        )
        Item(
            icon = painterResource(id = R.drawable.icons_invoice_invoice),
            title = stringResource(id = R.string.invoice_info),
            onClick = {

            }
        )
        Item(
            icon = painterResource(id = R.drawable.icons_iconplacesave_iconplacesave),
            title = stringResource(id = R.string.place_saved),
            onClick = {

            },
            true
        )
    }

    Column(
        modifier = Modifier
            .padding(top = 10.sdp, bottom = 30.sdp)
            .padding(horizontal = 15.sdp)
            .shadow(
                elevation = 1.sdp,
                shape = RoundedCornerShape(5)
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(5))
            .background(Color.White)
            .padding(horizontal = 12.sdp)
    ) {
        Item(
            icon = painterResource(id = R.drawable.images_support_ico_support),
            title = stringResource(id = R.string.support_center),
            onClick = {

            }
        )
        Item(
            icon = painterResource(id = R.drawable.images_policy_policy),
            title = stringResource(id = R.string.policy),
            onClick = {

            }
        )
        Item(
            icon = painterResource(id = R.drawable.images_languague_ico_languague),
            title = stringResource(id = R.string.language),
            onClick = {

            },
            true
        )
    }
}

@Composable
private fun Item(
    icon: Painter,
    title: String,
    onClick: () -> Unit,
    isLastItem: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.sdp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(18.sdp)
            )
            AppText(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.sdp, bottom = 3.sdp),
                fontSize = 11.ssp,
                color = Color.Black,
            )
            TouchableOpacityButton(
                onClick = {
                    onClick()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 7.sdp)
                        .height(10.sdp)
                )
            }
        }
        if (!isLastItem) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = colorResource(id = R.color.gray_200))
            )
        }
    }
}