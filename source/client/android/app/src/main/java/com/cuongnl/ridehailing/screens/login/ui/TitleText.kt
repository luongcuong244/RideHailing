package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.custom_view.AppText
import ir.kaaveh.sdpcompose.ssp

@Composable
fun TitleText(){
    AppText(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Light, color = Color.Black, fontSize = 12.ssp)) {
                append(stringResource(id = R.string.log_in_text_title_part_1))
            }
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, color = Color.Black, fontSize = 12.ssp)) {
                append(stringResource(id = R.string.log_in_text_title_part_2))
            }
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Light, color = Color.Black, fontSize = 12.ssp)) {
                append(stringResource(id = R.string.log_in_text_title_part_3))
            }
        },
    )
}
