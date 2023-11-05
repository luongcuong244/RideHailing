package com.cuongnl.ridehailing.screens.home.tab.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Notification
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.NotificationAppBar
import com.cuongnl.ridehailing.viewmodel.apiservice.UserServiceViewModel

@Composable
fun NotificationTab(userServiceViewModel: UserServiceViewModel = viewModel()) {

    LaunchedEffect(null) {
        getNotifications(userServiceViewModel)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NotificationAppBar()
    }
}

private fun getNotifications(userServiceViewModel: UserServiceViewModel) {

    CurrentUser.getUser()?.clearNotifications()
    CurrentUser.getUser()?.apply {

        val notifications = mutableListOf<Notification>().apply {
            add(
                Notification(
                    id = 1,
                    title = "Chào mừng bạn đến với ứng dụng",
                    content = "Chào mừng bạn đến với ứng dụng",
                    shortContent = "Chào mừng bạn đến với ứng dụng",
                    date = "01/11/2023",
                    isRead = false,
                    base64Image = ""
                )
            )
            add(
                Notification(
                    id = 2,
                    title = "Chào mừng bạn đến với ứng dụng",
                    content = "Chào mừng bạn đến với ứng dụng",
                    shortContent = "Chào mừng bạn đến với ứng dụng",
                    date = "01/11/2023",
                    isRead = true,
                    base64Image = ""
                )
            )
        }

        addNotifications(notifications)
    }

//    userServiceViewModel.getNotifications(object : SimpleApiCallback<NotificationResponse> {
//        override fun onSuccess(call: Call<NotificationResponse>, response: Response<NotificationResponse>) {
//            response.body()?.let {
//                CurrentUser.getUser()?.clearNotifications()
//                CurrentUser.getUser()?.addNotifications(it.notifications)
//            }
//        }
//
//        override fun onFinish() {
//
//        }
//
//        override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
//
//        }
//
//        override fun onError(
//            call: Call<NotificationResponse>,
//            response: Response<NotificationResponse>
//        ) {
//
//        }
//    })
}