package com.cuongnl.ridehailing.screens.booking

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
import com.google.maps.model.TravelMode

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

        val destinationLocationLatLng = intent.getParcelableExtra<LatLng>(Constant.BUNDLE_DESTINATION_LAT_LNG)
        val destinationLocationAddress = intent.getStringExtra(Constant.BUNDLE_DESTINATION_ADDRESS)
        val pickupLocationLatLng = intent.getParcelableExtra<LatLng>(Constant.BUNDLE_PICKUP_LAT_LNG)
        val pickupLocationAddress = intent.getStringExtra(Constant.BUNDLE_PICKUP_ADDRESS)

        bookingActivityUiViewModel.setDestinationLocationLatLng(destinationLocationLatLng!!)
        bookingActivityUiViewModel.setDestinationLocationAddress(destinationLocationAddress!!)
        bookingActivityUiViewModel.setPickupLocationLatLng(pickupLocationLatLng!!)
        bookingActivityUiViewModel.setPickupLocationAddress(pickupLocationAddress!!)

        val result = MapUtils.getDirectionsBetweenTwoPoints(
            destinationLocationLatLng,
            pickupLocationLatLng,
            TravelMode.DRIVING
        )

        if (result.routes.isNotEmpty()) {
            val points = mutableListOf<LatLng>()

            val startLocation = result.routes[0].legs[0].startLocation
            val newStartLocation = LatLng(startLocation.lat, startLocation.lng)

            points.add(newStartLocation)

            result.routes[0].legs[0].steps.forEach {
                val endLocation = it.endLocation
                val newEndLocation = LatLng(endLocation.lat, endLocation.lng)
                points.add(newEndLocation)
            }

            bookingActivityUiViewModel.setPoints(points)
        } else {
            Toast.makeText(this, "Cannot get directions", Toast.LENGTH_SHORT).show()
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