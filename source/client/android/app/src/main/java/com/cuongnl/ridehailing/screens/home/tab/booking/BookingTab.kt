package com.cuongnl.ridehailing.screens.home.tab.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.AddressesList
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.BannerVouchers
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.DashboardBanner
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.TransportationMethodsList
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.WhereDoYouWantToGo
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BookingTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DashboardBanner()

        Column(
            modifier = Modifier
                .offset(y = (-30).sdp)
                .weight(1f)
                .padding(horizontal = 10.sdp)
        ) {
            WhereDoYouWantToGo()
            AddressesList()
            TransportationMethodsList()
            BannerVouchers()
        }
    }
}