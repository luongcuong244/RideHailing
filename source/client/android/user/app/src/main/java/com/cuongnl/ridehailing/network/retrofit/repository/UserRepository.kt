package com.cuongnl.ridehailing.network.retrofit.repository

import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.models.api.AddressResponse
import com.cuongnl.ridehailing.models.api.ChangePasswordRequest
import com.cuongnl.ridehailing.models.api.ChangePasswordResponse
import com.cuongnl.ridehailing.models.api.GetBillsResponse
import com.cuongnl.ridehailing.models.api.GetUserResponse
import com.cuongnl.ridehailing.models.api.UpdateProfileRequest
import com.cuongnl.ridehailing.network.retrofit.RetrofitClient
import com.cuongnl.ridehailing.network.retrofit.api.UserApi
import retrofit2.Callback

class UserRepository {

    private val userApi: UserApi = RetrofitClient.getClient().create(UserApi::class.java)

    fun changePassword(changePasswordRequest: ChangePasswordRequest, callback: Callback<ChangePasswordResponse>) {
        userApi.changePassword(changePasswordRequest).enqueue(callback)
    }

    fun getUser(callback: Callback<GetUserResponse>) {
        userApi.getUser().enqueue(callback)
    }

    fun getUserAddresses(callback: Callback<AddressResponse>) {
        userApi.getUserAddresses().enqueue(callback)
    }

    fun addUserAddress(request: Address, callback: Callback<Void>) {
        userApi.addAddress(request).enqueue(callback)
    }

    fun getBills(callback: Callback<GetBillsResponse>) {
        userApi.getBills().enqueue(callback)
    }

    fun updateProfile(request: UpdateProfileRequest, callback: Callback<Void>) {
        userApi.updateProfile(request).enqueue(callback)
    }
}