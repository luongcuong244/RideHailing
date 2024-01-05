package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.extensions.findActivity

class SavedAddressUiViewModel : ViewModel() {

    fun onClickBackButton(context: Context) {
        context.findActivity()?.onBackPressed()
    }
}