package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.models.TripInfo

class DropoffConfirmationUiViewModel : ViewModel() {private lateinit var _currentTripInfo : TripInfo

        val currentTripInfo : TripInfo
                get() = _currentTripInfo

        fun setCurrentTripInfo(tripInfo : TripInfo) {
                _currentTripInfo = tripInfo
        }

        fun onClickDirectionButton(context: Context) {

                val uriString = "http://maps.google.com/maps?daddr=${currentTripInfo.destinationAddress.latitude},${currentTripInfo.destinationAddress.longitude}"

                val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(uriString)
                )
                context.startActivity(intent)
        }

        fun onClickTextingButton(context: Context) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("smsto:${currentTripInfo.userInfo.phoneNumber}")
                context.startActivity(intent)
        }

        fun onClickCallButton(context: Context) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${currentTripInfo.userInfo.phoneNumber}")
                context.startActivity(intent)
        }

        fun onClickCancelTripButton(context: Context) {

        }

        fun onSwipeToConfirm(context: Context) {

        }
}