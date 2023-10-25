package com.cuongnl.ridehailing.screens.otp_verification.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun OtpDescriptionText() {
    AppText(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = colorResource(id = R.color.gray_600), fontSize = 14.sp)) {
                append(stringResource(id = R.string.app_name))
            }
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = colorResource(id = R.color.gray_600), fontSize = 14.sp)) {
                append(stringResource(id = R.string.otp_des_text))
            }
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.gray_800), fontSize = 14.sp)) {
                append("0972085801")
            }
        },
        modifier = Modifier
            .padding(start = 10.sdp, end = 10.sdp, top = 8.sdp, bottom = 30.sdp),
        lineHeight = 22.sp,
    )
}