package com.cuongnl.ridehailing.screens.home.tab.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Notification
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.NotificationAppBar
import com.cuongnl.ridehailing.screens.home.tab.notification.ui.NotificationsList
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

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            NotificationsList()
        }
    }
}

private fun getNotifications(userServiceViewModel: UserServiceViewModel) {

    CurrentUser.getUser()?.clearNotifications()
    CurrentUser.getUser()?.apply {

        val notifications = mutableListOf<Notification>().apply {
            add(
                Notification(
                    id = 1,
                    date = "05/11/2023",
                    title = "Bike tặng Hà Nội tới 20K \uD83D\uDE0D",
                    shortContent = "⛅ 2 mã 10% tối đa 20k\n\uD83C\uDF08 Mở app đặt xe ngay",
                    content = "Chào mừng bạn đến với ứng dụng",
                    isRead = false,
                    base64Image = ""
                )
            )
            add(
                Notification(
                    id = 2,
                    date = "03/11/2023",
                    title = "Mã 15% Bike tặng Sài Gòn",
                    shortContent = "\uD83D\uDE0D Đặt Cam SM Bike bon bon\n\uD83D\uDC4F Mưa hay nắng bác tài Cam vẫn đón\n\uD83D\uDC9A Mở app đặt xe ưu đãi tới 40%",
                    content = "Chào mừng bạn đến với ứng dụng",
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