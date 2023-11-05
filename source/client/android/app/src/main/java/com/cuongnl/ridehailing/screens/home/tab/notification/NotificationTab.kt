package com.cuongnl.ridehailing.screens.home.tab.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.NotificationAppBar

@Composable
fun NotificationTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NotificationAppBar()
    }
}