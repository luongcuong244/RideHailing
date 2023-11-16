package com.cuongnl.ridehailing.screens.booking

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.screens.booking.ui.BackButton
import com.cuongnl.ridehailing.screens.booking.ui.FareCalculationInfoBottomSheet
import com.cuongnl.ridehailing.screens.booking.ui.BottomView
import com.cuongnl.ridehailing.screens.booking.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.google.android.gms.maps.model.LatLng
import ir.kaaveh.sdpcompose.sdp

@Suppress("DEPRECATION")
class BookingActivity : BaseActivity() {

    private lateinit var bookingActivityUiViewModel: BookingActivityUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        bookingActivityUiViewModel = ViewModelProvider(this)[BookingActivityUiViewModel::class.java]

        val destinationLocationLatLng =
            intent.getParcelableExtra<LatLng>(Constant.BUNDLE_DESTINATION_LAT_LNG)
        val destinationLocationAddress = intent.getStringExtra(Constant.BUNDLE_DESTINATION_ADDRESS)
        val pickupLocationLatLng = intent.getParcelableExtra<LatLng>(Constant.BUNDLE_PICKUP_LAT_LNG)
        val pickupLocationAddress = intent.getStringExtra(Constant.BUNDLE_PICKUP_ADDRESS)
        val transportationType = intent.getSerializableExtra(Constant.BUNDLE_TRAVEL_MODE) as TransportationType?

        bookingActivityUiViewModel.setDestinationLocationLatLng(destinationLocationLatLng!!)
        bookingActivityUiViewModel.setDestinationLocationAddress(destinationLocationAddress!!)
        bookingActivityUiViewModel.setPickupLocationLatLng(pickupLocationLatLng!!)
        bookingActivityUiViewModel.setPickupLocationAddress(pickupLocationAddress!!)

        if (transportationType != null) {
            bookingActivityUiViewModel.selectBookingInfoAndUpdateUI(this, transportationType)
        } else {
            bookingActivityUiViewModel.selectBookingInfoAndUpdateUI(this, TransportationType.TAXI)
        }

        bookingActivityUiViewModel.getBookingInfoResponses(this)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                BackButton()
                MapView()

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(10.sdp)
                        .clip(RoundedCornerShape(topStart = 20.sdp, topEnd = 20.sdp))
                        .background(Color.White)
                )
            }
            BottomView()
        }

        FareCalculationInfoBottomSheet()
    }
}