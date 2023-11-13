package com.cuongnl.ridehailing.screens.selectinglocation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.ISelectingLocationActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.selectinglocation.ui.AppBar
import com.cuongnl.ridehailing.screens.selectinglocation.ui.FetchAddressResponses
import com.cuongnl.ridehailing.screens.selectinglocation.ui.SavedAddresses
import com.cuongnl.ridehailing.screens.selectinglocation.ui.SearchingLocation
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.SelectingLocationUiViewModel
import com.google.android.gms.maps.model.LatLng
import ir.kaaveh.sdpcompose.sdp

val LocalActivityBehavior =
    staticCompositionLocalOf<ISelectingLocationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class SelectingLocationActivity : BaseActivity(), ISelectingLocationActivityBehavior {

    private lateinit var selectingLocationUiViewModel: SelectingLocationUiViewModel

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
        selectingLocationUiViewModel = ViewModelProvider(this)[SelectingLocationUiViewModel::class.java]

        MapUtils.getCurrentLocation(
            this,
            onSuccess = {
                selectingLocationUiViewModel.setCurrentLocationLatLng(LatLng(it.latitude, it.longitude))
            },
            onFailure = {
                Toast.makeText(this, "Failed to get current location", Toast.LENGTH_SHORT).show()
            },
            onPermissionNotGranted = {
                Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.app_background_gray))
                .statusBarsPadding()
                .padding(top = 8.dp)
                .padding(horizontal = 10.sdp)
        ) {
            AppBar()

            ConstraintLayout {
                val (searchingLocation, savedAddresses, fetchAddressResponses) = createRefs()

                SavedAddresses(
                    modifier = Modifier
                        .constrainAs(savedAddresses) {
                            top.linkTo(searchingLocation.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                FetchAddressResponses(
                    modifier = Modifier
                        .constrainAs(fetchAddressResponses) {
                            top.linkTo(searchingLocation.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .offset(y = (-20).sdp)
                )

                SearchingLocation(
                    modifier = Modifier
                        .constrainAs(searchingLocation) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }
}