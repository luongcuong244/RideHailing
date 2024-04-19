package com.cuongnl.ridehailing.screens.editprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.ssp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.EditProfileUiViewModel
import com.cuongnl.ridehailing.widgets.TextFieldWithBorder
import ir.kaaveh.sdpcompose.sdp

@Composable
fun NameTextField(
    editProfileUiViewModel: EditProfileUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = 10.sdp)
            .padding(horizontal = 15.sdp)
            .fillMaxWidth()
    ) {
        AppText(
            text = stringResource(id = R.string.user_name_text),
            fontSize = 11.ssp,
            color = colorResource(id = R.color.gray_800),
            modifier = Modifier
                .padding(start = 10.sdp)
                .fillMaxWidth()
        )

        TextFieldWithBorder(
            ref = editProfileUiViewModel.getNameTextFieldValue(),
            onValueChange = {
                editProfileUiViewModel.setNameTextField(it.text)
            },
            textSize = 12.ssp,
            textColor = Color.Black,
            placeholder = stringResource(id = R.string.user_name_hint),
            modifier = Modifier
                .padding(top = 5.sdp)
                .background(colorResource(id = R.color.gray_100).copy(0.7f)),
            activeBorderColor = R.color.gray_300,
            fontWeight = FontWeight.SemiBold
        )
    }
}