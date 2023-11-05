package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NotificationTabUiViewModel : ViewModel() {

    private var _isDeleting = mutableStateOf(false)

    val isDeleting : State<Boolean> = _isDeleting

    val listNotificationsToDelete = mutableListOf<Int>()

    fun setIsDeleting(isDeleting: Boolean) {

        if (!isDeleting) {
            listNotificationsToDelete.clear()
        }

        _isDeleting.value = isDeleting
    }
}