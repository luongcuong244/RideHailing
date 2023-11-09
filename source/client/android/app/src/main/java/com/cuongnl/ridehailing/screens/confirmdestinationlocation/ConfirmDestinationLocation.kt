package com.cuongnl.ridehailing.screens.confirmdestinationlocation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.confirmdestinationlocation.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.MapUtils
import com.cuongnl.ridehailing.viewmodel.ConfirmDestinationLocationViewModel

class ConfirmDestinationLocation : BaseActivity() {

    private lateinit var confirmDestinationLocationViewModel: ConfirmDestinationLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {

        confirmDestinationLocationViewModel = ViewModelProvider(this)[ConfirmDestinationLocationViewModel::class.java]

        MapUtils.getCurrentLocation(
            this,
            onSuccess = {
                confirmDestinationLocationViewModel.setSelectedLocationAndLoadAddress(this, it)
            }
        )
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MapView()
        }
    }
}