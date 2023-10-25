package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.utils.beVietNamFamily
import com.cuongnl.ridehailing.viewmodel.CountryCodeBottomSheetViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SearchBar(countryCodeBottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()){

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }

    val borderColorId = remember {
        mutableStateOf(R.color.gray_300)
    }

    Box(
        modifier = Modifier
            .padding(bottom = 20.sdp)
            .clip(RoundedCornerShape(8.sdp))
            .fillMaxWidth()
            .height(40.sdp)
            .border(
                1.dp,
                colorResource(id = borderColorId.value),
                RoundedCornerShape(8.sdp)
            ),
    ) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                countryCodeBottomSheetViewModel.filterCountryCodeList(it.text)
            },
            modifier = Modifier
                .padding(horizontal = 10.sdp)
                .align(Alignment.Center)
                .fillMaxWidth()
                .onFocusChanged {
                    borderColorId.value =
                        if (it.isFocused)
                            R.color.app_color
                        else
                            R.color.gray_300
                },
            textStyle = TextStyle(
                fontSize = 13.ssp,
                fontFamily = beVietNamFamily,
                color = colorResource(R.color.black),
                lineHeight = 24.ssp,
            ),
            decorationBox = {innerTextField ->
                if (textFieldValue.text.isEmpty()) {
                    AppText(
                        text = stringResource(id = R.string.search_text),
                        fontSize = 13.ssp,
                        color = colorResource(R.color.gray_600),
                        lineHeight = 24.ssp,
                    )
                }
                innerTextField()
            },
            maxLines = 1
        )
    }
}