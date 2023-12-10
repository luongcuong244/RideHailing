package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.enums.SelectingLocationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.screens.addingaddress.AddingAddressActivity
import com.cuongnl.ridehailing.screens.confirmlocation.ConfirmLocationActivity
import com.cuongnl.ridehailing.screens.selectinglocation.SelectingLocationActivity
import com.cuongnl.ridehailing.utils.Constant
import com.google.android.gms.maps.model.LatLng

class BookingTabUiViewModel(application: Application) : AndroidViewModel(application) {

    private var _isLoadingAddress = mutableStateOf(true)

    val isLoadingAddress: State<Boolean> = _isLoadingAddress

    fun setIsLoadingAddress(isLoadingAddress: Boolean) {
        _isLoadingAddress.value = isLoadingAddress
    }

    fun onClickAddAddress(context: Context) {

        var addressType = AddressType.OTHER

        val indexOfHome = CurrentUser.getUser()?.addresses?.indexOfFirst {
            it.addressType == AddressType.HOME
        }

        val indexOfWork = CurrentUser.getUser()?.addresses?.indexOfFirst {
            it.addressType == AddressType.WORK
        }

        if (indexOfHome == -1) {
            addressType = AddressType.HOME
        } else if (indexOfWork == -1) {
            addressType = AddressType.WORK
        }

        val intent = Intent(context, AddingAddressActivity::class.java)
        intent.putExtra(Constant.BUNDLE_ADDRESS_TYPE, addressType)

        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun onClickWhereDoYouWantToGo(context: Context) {
        val intent = Intent(context, SelectingLocationActivity::class.java)

        intent.putExtra(Constant.BUNDLE_DESTINATION_LAT_LNG, CurrentLocation.getLatLng())
        intent.putExtra(Constant.BUNDLE_DESTINATION_ADDRESS, CurrentLocation.getFullAddress())
        intent.putExtra(Constant.BUNDLE_PICKUP_LAT_LNG, CurrentLocation.getLatLng())
        intent.putExtra(Constant.BUNDLE_PICKUP_ADDRESS, CurrentLocation.getFullAddress())
        intent.putExtra(Constant.BUNDLE_SELECTING_LOCATION_TYPE, SelectingLocationType.DESTINATION_LOCATION)

        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun onClickAddressItem(context: Context, address: Address) {
        val intent = Intent(context, ConfirmLocationActivity::class.java)

        intent.putExtra(Constant.BUNDLE_DESTINATION_LAT_LNG, LatLng(address.latitude, address.longitude))
        intent.putExtra(Constant.BUNDLE_DESTINATION_ADDRESS, address.fullName)

        context.startActivity(intent)
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}