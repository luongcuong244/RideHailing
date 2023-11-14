package com.cuongnl.ridehailing.screens.booking

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.booking.ui.BackButton
import com.cuongnl.ridehailing.screens.booking.ui.BottomView
import com.cuongnl.ridehailing.screens.booking.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.internal.PolylineEncoding
import com.google.maps.model.TravelMode

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
        val travelMode = intent.getSerializableExtra(Constant.BUNDLE_TRAVEL_MODE) as TravelMode?

        bookingActivityUiViewModel.setDestinationLocationLatLng(destinationLocationLatLng!!)
        bookingActivityUiViewModel.setDestinationLocationAddress(destinationLocationAddress!!)
        bookingActivityUiViewModel.setPickupLocationLatLng(pickupLocationLatLng!!)
        bookingActivityUiViewModel.setPickupLocationAddress(pickupLocationAddress!!)

        if (travelMode != null) {
            bookingActivityUiViewModel.setTravelModeAndUpdateUI(this, travelMode)
        } else {
            bookingActivityUiViewModel.setTravelModeAndUpdateUI(this, TravelMode.DRIVING)
        }
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
            }
            BottomView()
        }
    }
}