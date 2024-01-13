package com.cuongnl.ridehailing.screens.home.tab.account

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.screens.home.tab.account.ui.Body
import com.cuongnl.ridehailing.screens.home.tab.account.ui.Header
import com.cuongnl.ridehailing.screens.home.tab.account.ui.LanguageBottomSheet

@Composable
fun AccountTab() {

    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = state,
                orientation = Orientation.Vertical
            )
    ) {
        Header()
        Body()
        LanguageBottomSheet()
    }
}