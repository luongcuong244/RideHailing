package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.network.retrofit.repository.UserRepository
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddingAddressUiViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private lateinit var _addressType: AddressType

    private val _addressName = mutableStateOf(CurrentLocation.getFullAddress())
    private val _selectedLatLng = mutableStateOf(CurrentLocation.getLatLng())

    val addressName: State<String> = _addressName
    val selectedLatLng: State<LatLng> = _selectedLatLng

    fun setAddressType(addressType: AddressType) {
        _addressType = addressType
    }

    fun setSelectedLatLngAndLoadAddress(context: Context, latLng: LatLng) {
        _selectedLatLng.value = latLng
        loadAddress(context, latLng)
    }

    fun onClickBackButton(context: Context) {
        backToPrevScreen(context)
    }

    fun onClickConfirmButton(
        context: Context,
        onFinished: () -> Unit = {}
    ) {

        val request = Address(
            _addressType,
            FormatterUtils.getShortAddress(_addressName.value),
            _addressName.value,
            _selectedLatLng.value.longitude,
            _selectedLatLng.value.latitude
        )

        userRepository.addUserAddress(request, object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                backToPrevScreen(context)
                onFinished()
                CurrentUser.getUser()?.addAddress(request)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                backToPrevScreen(context)
                onFinished()
                Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun backToPrevScreen(context: Context) {
        context.findActivity()?.finish()
        context.findActivity()?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun loadAddress(context: Context, latLng: LatLng) {
        MapUtils.getAddressByCoordinates(
            context,
            latLng,
            onSuccess = {
                _addressName.value = it
            },
        )
    }
}