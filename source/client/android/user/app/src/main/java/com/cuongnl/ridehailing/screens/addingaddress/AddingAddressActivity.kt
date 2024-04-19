package com.cuongnl.ridehailing.screens.addingaddress

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.screens.addingaddress.ui.AddressText
import com.cuongnl.ridehailing.screens.addingaddress.ui.AppBar
import com.cuongnl.ridehailing.screens.addingaddress.ui.ConfirmButton
import com.cuongnl.ridehailing.screens.addingaddress.ui.MapView
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.AddingAddressUiViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader

class AddingAddressActivity : BaseActivity() {

    private lateinit var addingAddressUiViewModel: AddingAddressUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            Screen()
        }
    }

    private fun setupViewModel() {
        addingAddressUiViewModel = ViewModelProvider(this)[AddingAddressUiViewModel::class.java]

        val addressType = intent.getSerializableExtra(Constant.BUNDLE_ADDRESS_TYPE) as AddressType
        addingAddressUiViewModel.setAddressType(addressType)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AppBar()

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    MapView()
                    AddressText()
                    ConfirmButton()
                }
            }
        }
    }
}