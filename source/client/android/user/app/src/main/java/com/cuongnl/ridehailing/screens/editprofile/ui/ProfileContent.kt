package com.cuongnl.ridehailing.screens.editprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ColumnScope.ProfileContent() {
    Column(
        modifier = Modifier
            .padding(top = 5.sdp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 10.sdp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar()
        NameTextField()
        EmailTextField()
    }
}