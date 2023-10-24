package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.custom_view.AppText
import com.cuongnl.ridehailing.enums.NumberPhone
import com.cuongnl.ridehailing.viewmodel.CountryCodeBottomSheetViewModel
import com.cuongnl.ridehailing.viewmodel.NumberPhoneSelectedViewModel
import com.translator.voicechanger.monster.voicetranslator.ui.common.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CountryCodeList() {

    val size = NumberPhone.values().size

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.sdp),
        content = {
            items(size) {
                CountryCodeItem(it)
            }
        }
    )
}

@Composable
private fun CountryCodeItem(
    index: Int,
    numberPhoneSelectedViewModel: NumberPhoneSelectedViewModel = viewModel(),
    countryCodeBottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()
){

    val item = NumberPhone.values()[index]

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .clickable {
                numberPhoneSelectedViewModel.setCurrentNumberPhone(item)
                countryCodeBottomSheetViewModel.setBottomSheetVisible(false)
            }
    ) {
        CountryFlag(item.countryFlag)
        CountryName(item.countryName)
        CountryCode(item.phoneCode)
    }
}

@Composable
private fun CountryFlag(imageId: Int){
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier
            .width(30.sdp)
    )
}

@Composable
private fun CountryName(name: String){
    AppText(
        text = name,
        fontSize = 15.sp,
        color = colorResource(id = R.color.black),
        modifier = Modifier
            .padding(start = 10.sdp)
    )
}

@Composable
private fun RowScope.CountryCode(code: String){
    AppText(
        text = code,
        fontSize = 16.sp,
        color = colorResource(id = R.color.black),
        modifier = Modifier
            .weight(1f),
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Bold,
    )
}