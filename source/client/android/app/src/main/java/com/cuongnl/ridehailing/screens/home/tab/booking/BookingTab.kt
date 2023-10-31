package com.cuongnl.ridehailing.screens.home.tab.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.DashboardBanner

@Composable
fun BookingTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DashboardBanner()
    }
}