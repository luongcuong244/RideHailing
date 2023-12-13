package com.cuongnl.ridehailing.screens.editprofile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.viewmodel.EditProfileUiViewModel
import com.cuongnl.ridehailing.widgets.ToggleableButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BoxScope.UpdateButton(
    editProfileUiViewModel: EditProfileUiViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .padding(bottom = 10.sdp)
            .padding(horizontal = 20.sdp)
            .align(Alignment.BottomCenter)
    ) {
        ToggleableButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(37.sdp),
            onClick = {

            },
            text = stringResource(id = R.string.update_text),
            textSize = 12.ssp,
            inactiveBackground = colorResource(id = R.color.gray_500),
            enable = editProfileUiViewModel.getNameTextFieldValue().value.text != CurrentUser.getUser()?.userName?.value!!
                    || editProfileUiViewModel.getEmailTextFieldValue().value.text != CurrentUser.getUser()?.email?.value!!
        )
    }
}