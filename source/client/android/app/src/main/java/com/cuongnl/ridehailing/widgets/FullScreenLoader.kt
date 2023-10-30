package com.cuongnl.ridehailing.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.FullScreenLoaderViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun FullScreenLoader(
    fullScreenLoaderViewModel: FullScreenLoaderViewModel = viewModel(),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        content()

        if (fullScreenLoaderViewModel.isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                    }
            ) {
                LoadingAnimation(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(7.sdp))
                        .background(colorResource(id = R.color.gray_500))
                        .width(40.sdp)
                        .height(35.sdp)
                        .padding(top = 2.sdp)
                )
            }
        }
    }
}