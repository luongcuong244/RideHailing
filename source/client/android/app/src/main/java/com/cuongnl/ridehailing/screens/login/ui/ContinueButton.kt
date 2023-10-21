package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.custom_view.AppText
import com.cuongnl.ridehailing.screens.login.LocalActivityBehavior
import com.cuongnl.ridehailing.viewmodel.NumberPhoneSelectedViewModel
import com.cuongnl.ridehailing.viewmodel.TextEnteredViewModel
import com.translator.voicechanger.monster.voicetranslator.ui.common.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ContinueButton(
    textEnteredViewModel: TextEnteredViewModel = viewModel(),
    phoneSelectedViewModel: NumberPhoneSelectedViewModel = viewModel()
){

    val actions = LocalActivityBehavior.current

    val textEnteredLength = textEnteredViewModel.text.value.length
    val maxLength = phoneSelectedViewModel.currentNumberPhone.value.maxLength
    val minLength = phoneSelectedViewModel.currentNumberPhone.value.minLength

    val isPhoneValid = textEnteredLength in minLength..maxLength

    val buttonColor = if (isPhoneValid) {
        colorResource(id = R.color.app_color)
    } else {
        colorResource(id = R.color.gray_500)
    }

    TouchableOpacityButton(
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(9.sdp))
            .background(buttonColor)
            .padding(vertical = 9.sdp),
        onClick = {
            if(isPhoneValid){
                actions.onContinueButtonClicked()
            }
        }
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.continue_text),
            fontSize = 14.ssp,
            color = colorResource(id = R.color.white)
        )
    }
}