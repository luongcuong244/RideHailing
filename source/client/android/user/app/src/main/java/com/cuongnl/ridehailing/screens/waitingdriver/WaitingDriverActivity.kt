package com.cuongnl.ridehailing.screens.waitingdriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.DriverInfoResponse
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.WaitingDriverUiViewModel

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

        waitingDriverUiViewModel.setDriverInfoResponse(driverInfo)
    }
}

@Composable
private fun Screen() {
    AppTheme {

    }
}