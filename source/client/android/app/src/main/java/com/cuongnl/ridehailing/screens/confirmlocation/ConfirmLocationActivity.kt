package com.cuongnl.ridehailing.screens.confirmlocation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.activitybehavior.IConfirmDestinationLocationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.ConfirmLocationState
import com.cuongnl.ridehailing.screens.confirmlocation.ui.BackButton
import com.cuongnl.ridehailing.screens.confirmlocation.ui.BottomView
import com.cuongnl.ridehailing.screens.confirmlocation.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.ConfirmLocationViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import com.google.android.gms.maps.model.LatLng

val LocalActivityBehavior = androidx.compose.runtime.staticCompositionLocalOf<IConfirmDestinationLocationActivityBehavior> {
    error("No ActivityBehavior provided")
}

class ConfirmLocationActivity : BaseActivity(), IConfirmDestinationLocationActivityBehavior {

    private lateinit var confirmLocationViewModel: ConfirmLocationViewModel
    private lateinit var loaderViewModel: LoaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    private fun setupViewModel() {

        confirmLocationViewModel = ViewModelProvider(this)[ConfirmLocationViewModel::class.java]
        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]

        loaderViewModel.setLoading(true)

        MapUtils.getCurrentLocation(
            this,
            onSuccess = {
                val latLng = LatLng(it.latitude, it.longitude)
                confirmLocationViewModel.setDestinationLocationLatLngAndLoadAddress(this, latLng)
                confirmLocationViewModel.setCurrentLocationLatLng(latLng)
                loaderViewModel.setLoading(false)
            },
            onFailure = {
                loaderViewModel.setLoading(false)
                Toast.makeText(this, "Cannot get current location", Toast.LENGTH_SHORT).show()
            },
        )
    }

    override fun editLocation() {

    }

    override fun onClickConfirmButton() {
        when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {

                confirmLocationViewModel.setConfirmLocationState(ConfirmLocationState.CHOOSING_PICKUP_LOCATION)

                if (confirmLocationViewModel.pickupLocationLatLng.value == null) {
                    confirmLocationViewModel.setPickupLocationLatLngAndLoadAddress(this, confirmLocationViewModel.currentLocationLatLng.value!!)
                }
            }
            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                //navigateToConfirmPickupLocationActivity()
            }
        }
    }

    override fun onClickBackButton() {
        when (confirmLocationViewModel.confirmLocationState.value) {
            ConfirmLocationState.CHOOSING_DESTINATION_LOCATION -> {
                finish()
            }
            ConfirmLocationState.CHOOSING_PICKUP_LOCATION -> {
                confirmLocationViewModel.setConfirmLocationState(ConfirmLocationState.CHOOSING_DESTINATION_LOCATION)
            }
        }
    }
}

@Composable
private fun Screen() {
    AppTheme {
        FullScreenLoader {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MapView()
                BottomView()
                BackButton()
            }
        }
    }
}