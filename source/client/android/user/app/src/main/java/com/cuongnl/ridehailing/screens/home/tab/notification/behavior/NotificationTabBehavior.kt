package com.cuongnl.ridehailing.screens.home.tab.notification.behavior

import android.content.Context
import android.content.Intent
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.extensions.showDialog
import com.cuongnl.ridehailing.models.api.RemoveNotificationsRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import com.cuongnl.ridehailing.screens.notificationdetails.NotificationDetailActivity
import com.cuongnl.ridehailing.viewmodel.NotificationTabUiViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.NotificationServiceViewModel
import retrofit2.Call
import retrofit2.Response

class NotificationTabBehavior(val context: Context) {

    fun getNotifications(
        notificationServiceViewModel: NotificationServiceViewModel,
        notificationTabUiViewModel: NotificationTabUiViewModel
    ) {

//    notificationServiceViewModel.getNotifications(object : SimpleApiCallback<NotificationResponse> {
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

    fun showDeleteAllNotificationDialog() {
        context.showDialog(
            title = context.getString(R.string.dialog_delete_all_notification_title),
            message = context.getString(R.string.dialog_delete_all_notification_content),
            textOfNegativeButton = context.getString(R.string.cancel),
            textOfPositiveButton = context.getString(R.string.delete),
            positiveButtonFunction = {

            }
        )
    }

    fun removeAllNotifications(notificationServiceViewModel: NotificationServiceViewModel) {
        notificationServiceViewModel.removeAllNotifications(
            object : SimpleApiCallback<ScalarsBooleanResponse> {
                override fun onSuccess(
                    call: Call<ScalarsBooleanResponse>,
                    response: Response<ScalarsBooleanResponse>
                ) {

                }

                override fun onFinish() {

                }

                override fun onFailure(call: Call<ScalarsBooleanResponse>, t: Throwable) {

                }

                override fun onError(
                    call: Call<ScalarsBooleanResponse>,
                    response: Response<ScalarsBooleanResponse>
                ) {

                }
            }
        )
    }

    fun removeNotifications(
        notificationTabUiViewModel: NotificationTabUiViewModel,
        notificationServiceViewModel: NotificationServiceViewModel,
    ) {

        val notificationIdsToDelete = NotificationTabUiViewModel.listNotifications.filter { it.isSelected.value }.map { it.notification.id }

        val request = RemoveNotificationsRequest(notificationIdsToDelete)

        // onSuccess
        notificationIdsToDelete.forEach {
            notificationTabUiViewModel.removeNotificationById(it)
        }

//        notificationServiceViewModel.removeNotifications(
//            request,
//            object : SimpleApiCallback<ScalarsBooleanResponse> {
//                override fun onSuccess(
//                    call: Call<ScalarsBooleanResponse>,
//                    response: Response<ScalarsBooleanResponse>
//                ) {
//
//                }
//
//                override fun onFinish() {
//
//                }
//
//                override fun onFailure(call: Call<ScalarsBooleanResponse>, t: Throwable) {
//
//                }
//
//                override fun onError(
//                    call: Call<ScalarsBooleanResponse>,
//                    response: Response<ScalarsBooleanResponse>
//                ) {
//
//                }
//            }
//        )
    }

    fun navigateToNotificationDetail() {
        val intent = Intent(context, NotificationDetailActivity::class.java)
        context.startActivity(intent)
    }
}