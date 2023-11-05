package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.models.AddressResponse
import com.cuongnl.ridehailing.retrofit.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository =
        UserRepository(getApplication<Application>().applicationContext)

    private var _isLoadingAddress = mutableStateOf(true)

    val isLoadingAddress: State<Boolean> = _isLoadingAddress

    init {
        Handler().postDelayed({
            CurrentUser.getUser()?.clearAddresses()
            CurrentUser.getUser()?.apply {
                addAddress(
                    Address(
                        AddressType.HOME,
                        "123 Nguyen Van Linh",
                        "777 Brockton Avenue, Abington MA 2351",
                        10.762622,
                        106.660172
                    )
                )
                addAddress(
                    Address(
                        AddressType.WORK,
                        "123 Nguyen Van Linh",
                        "30 Memorial Drive, Avon MA 2322",
                        10.762622,
                        106.660172
                    )
                )
                addAddress(
                    Address(
                        AddressType.OTHER,
                        "123 Nguyen Van Linh",
                        "337 Russell St, Hadley MA 1035",
                        10.762622,
                        106.660172
                    )
                )
            }
            setIsLoadingAddress(false)
        }, 2000)
        //getUserAddresses(application.applicationContext)
    }

    private fun getUserAddresses(context: Context) {
        userRepository.getUserAddresses(object : Callback<AddressResponse> {
            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        CurrentUser.getUser()?.clearAddresses()
                        CurrentUser.getUser()?.addAddresses(it.addresses)
                        setIsLoadingAddress(false)
                    }
                }
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    context.getText(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun setIsLoadingAddress(isLoadingAddress: Boolean) {
        _isLoadingAddress.value = isLoadingAddress
    }
}