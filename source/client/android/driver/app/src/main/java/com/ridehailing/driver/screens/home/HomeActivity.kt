package com.ridehailing.driver.screens.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ridehailing.driver.core.BaseActivity
import com.ridehailing.driver.globalstate.CurrentDriver
import com.ridehailing.driver.globalstate.CurrentLocation
import com.ridehailing.driver.models.api.DriverConnectToSocket
import com.ridehailing.driver.network.socketio.BookingSocket
import com.ridehailing.driver.screens.home.bottombar.BottomBar
import com.ridehailing.driver.screens.home.bottombar.BottomNavGraph
import com.ridehailing.driver.theme.AppTheme

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }

        CurrentLocation.fetchAddress(application)

        BookingSocket.connect()
        BookingSocket.emitDriverConnectToSocket(
            DriverConnectToSocket(
                CurrentDriver.getDriver().phoneNumber.value,
                CurrentLocation.latLng.value.latitude,
                CurrentLocation.latLng.value.longitude,
            ).toJson()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        BookingSocket.disconnect()
    }
}

@Composable
private fun Screen() {
    val navController = rememberNavController()

    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            BottomNavGraph(
                navController,
                modifier = Modifier
                    .weight(1f)
            )

            BottomBar(navController = navController)
        }
    }
}