package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.models.Notification
import com.cuongnl.ridehailing.models.item.NotificationItem

class NotificationTabUiViewModel : ViewModel() {

    private var _isDeleting = mutableStateOf(false)

    val isDeleting : State<Boolean> = _isDeleting

    companion object {
        val listNotifications = mutableStateListOf<NotificationItem>()
            .apply {
                add(
                    NotificationItem(
                        Notification(
                            id = 1,
                            date = "05/11/2023",
                            title = "Bike tặng Hà Nội tới 20K \uD83D\uDE0D",
                            shortContent = "⛅ 2 mã 10% tối đa 20k\n\uD83C\uDF08 Mở app đặt xe ngay",
                            content = "Chào mừng bạn đến với ứng dụng",
                            isRead = true,
                            base64Image = ""
                        )
                    )
                )
                add(
                    NotificationItem(
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
                )
            }
    }

    fun setIsDeleting(isDeleting: Boolean) {

        if (!isDeleting) {
            unSelectAllNotifications()
        }

        _isDeleting.value = isDeleting
    }

    fun addNotification(notification: NotificationItem) {
        listNotifications.add(notification)
    }

    fun removeNotificationById(notificationId: Int) {
        listNotifications.removeIf { it.notification.id == notificationId }
    }

    fun isMoreNotificationsSelected() : Boolean {
        return listNotifications.filter { it.isSelected.value }.size > 1
    }

    private fun unSelectAllNotifications() {
        listNotifications.forEach { it.isSelected.value = false }
    }
}