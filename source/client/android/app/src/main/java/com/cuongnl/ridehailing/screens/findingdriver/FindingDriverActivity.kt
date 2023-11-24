package com.cuongnl.ridehailing.screens.findingdriver

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.FindingDriverViewModel
import org.json.JSONObject

class FindingDriverActivity : BaseActivity() {

    private lateinit var findingDriverViewModel: FindingDriverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        findingDriverViewModel = ViewModelProvider(this)[FindingDriverViewModel::class.java]

        findingDriverViewModel.connect()
        findingDriverViewModel.setupListeners(this)

        val requestData = intent.getStringExtra(Constant.BUNDLE_REQUEST_A_RIDE_REQUEST)
        findingDriverViewModel.emitRequestARide(JSONObject(requestData))
    }

    override fun onDestroy() {
        super.onDestroy()
        findingDriverViewModel.disconnect()
    }
}

@Composable
private fun Screen() {
    AppTheme {

    }
}