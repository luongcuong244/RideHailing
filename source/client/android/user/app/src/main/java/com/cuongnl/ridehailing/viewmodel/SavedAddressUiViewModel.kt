package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.screens.addingaddress.AddingAddressActivity
import com.cuongnl.ridehailing.utils.Constant

class SavedAddressUiViewModel : ViewModel() {

    fun onClickBackButton(context: Context) {
        context.findActivity()?.onBackPressed()
    }

    fun navigateToAddAddressScreen(context: Context, addressType: AddressType) {
        val intent = Intent(context, AddingAddressActivity::class.java)
        intent.putExtra(Constant.BUNDLE_ADDRESS_TYPE, addressType)
        context.startActivity(intent)
    }
}