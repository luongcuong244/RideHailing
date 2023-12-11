package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.cuongnl.ridehailing.models.Bill
import com.cuongnl.ridehailing.models.api.GetBillsResponse
import com.cuongnl.ridehailing.network.retrofit.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryTabUiViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository()

    private val _bills = mutableStateListOf<Bill>()

    val bills : List<Bill> = _bills

    fun addBill(bill: Bill) {
        _bills.add(bill)
    }

    fun addBills(bills: List<Bill>) {
        _bills.addAll(bills)
    }

    fun fetchingBills(onFinished: () -> Unit = {}) {
        userRepository.getBills(object : Callback<GetBillsResponse> {
            override fun onResponse(
                call: Call<GetBillsResponse>,
                response: Response<GetBillsResponse>
            ) {
                val bills = response.body()?.bills
                bills?.let {
                    addBills(it)
                }
                onFinished()
            }

            override fun onFailure(call: Call<GetBillsResponse>, t: Throwable) {
                Toast.makeText(getApplication(), "Failed to get bills", Toast.LENGTH_SHORT).show()
                onFinished()
            }
        })
    }
}