package com.cuongnl.ridehailing.screens.waitingdriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.DriverInfoResponse
import com.cuongnl.ridehailing.screens.waitingdriver.ui.BottomView
import com.cuongnl.ridehailing.screens.waitingdriver.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.WaitingDriverUiViewModel
import com.google.android.gms.maps.model.LatLng

class WaitingDriverActivity : BaseActivity() {

    private lateinit var waitingDriverUiViewModel: WaitingDriverUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        waitingDriverUiViewModel = ViewModelProvider(this)[WaitingDriverUiViewModel::class.java]

        val driverInfo = intent.getSerializableExtra(Constant.BUNDLE_DRIVER_INFO_RESPONSE) as DriverInfoResponse
        val destinationLatLng = intent.getParcelableExtra<LatLng>(Constant.BUNDLE_DESTINATION_LAT_LNG)
        val minutesToDriverArrival = intent.getIntExtra(Constant.BUNDLE_MINUTES_TO_ARRIVE, 1)

        waitingDriverUiViewModel.setDriverInfoResponse(driverInfo)
        waitingDriverUiViewModel.setDestinationLatLng(destinationLatLng) 
        waitingDriverUiViewModel.setMinutesToDriverArrival(minutesToDriverArrival)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            MapView()
            BottomView()
        }
    }
}