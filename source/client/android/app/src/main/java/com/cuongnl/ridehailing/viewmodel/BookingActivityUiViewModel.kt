package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.models.api.GetBookingInfoRequest
import com.cuongnl.ridehailing.models.api.GetBookingInfoResponse
import com.cuongnl.ridehailing.models.item.RideBookingInfoItem
import com.cuongnl.ridehailing.retrofit.repository.BookingRepository
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.TravelMode

class BookingActivityUiViewModel : ViewModel() {

    private val bookingRepository = BookingRepository()

    val points = mutableStateListOf<LatLng>()

    val bookingsInfo = mutableStateListOf<RideBookingInfoItem>()

    private val _destinationLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _pickupLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _destinationLocationAddress = mutableStateOf("")
    private val _pickupLocationAddress = mutableStateOf("")

    val destinationLocationLatLng: State<LatLng> = _destinationLocationLatLng
    val pickupLocationLatLng: State<LatLng> = _pickupLocationLatLng
    val destinationLocationAddress: State<String> = _destinationLocationAddress
    val pickupLocationAddress: State<String> = _pickupLocationAddress

    init {
        TransportationType.values().forEach {
            bookingsInfo.add(
                RideBookingInfoItem(
                    transportationType = it,
                )
            )
        }
    }

    fun selectBookingInfoAndUpdateUI(context: Context, transportationType: TransportationType) {
        bookingsInfo.forEach {
            it.isSelected.value = it.transportationType == transportationType

            if (it.isSelected.value) {
                if (it.directionPoints == null) {
                    val points =
                        getDirectionsBetweenTwoPoints(context, transportationType.travelMode)
                    it.directionPoints = points

                }
                setPoints(it.directionPoints!!)
            }
        }
    }

    fun setPoints(points: List<LatLng>) {
        this.points.clear()
        this.points.addAll(points)
    }

    fun setDestinationLocationLatLng(destinationLocationLatLng: LatLng) {
        _destinationLocationLatLng.value = destinationLocationLatLng
    }

    fun setPickupLocationLatLng(pickupLocationLatLng: LatLng) {
        _pickupLocationLatLng.value = pickupLocationLatLng
    }

    fun setDestinationLocationAddress(destinationLocationAddress: String) {
        _destinationLocationAddress.value = destinationLocationAddress
    }

    fun setPickupLocationAddress(pickupLocationAddress: String) {
        _pickupLocationAddress.value = pickupLocationAddress
    }

    private fun getDirectionsBetweenTwoPoints(
        context: Context,
        travelMode: TravelMode
    ): List<LatLng> {

        val result = MapUtils.getDirectionsBetweenTwoPoints(
            destinationLocationLatLng.value,
            pickupLocationLatLng.value,
            travelMode,
        )

        if (result.routes.isNotEmpty()) {

            val points = mutableListOf<LatLng>()

            result.routes[0].legs[0].steps.forEach {
                points.addAll(it.polyline.decodePath().map { latLng ->
                    val newLat = LatLng(latLng.lat, latLng.lng)
                    newLat
                })
            }

            return points
        } else {
            Toast.makeText(context, "Cannot get directions", Toast.LENGTH_SHORT).show()
        }

        return listOf()
    }

    fun getBookingInfoResponses(context: Context) {

        bookingsInfo.forEach {

            val request = GetBookingInfoRequest(
                travelMode = it.transportationType.name,
                startLatitude = pickupLocationLatLng.value.latitude,
                startLongitude = pickupLocationLatLng.value.longitude,
                endLatitude = destinationLocationLatLng.value.latitude,
                endLongitude = destinationLocationLatLng.value.longitude,
            )

            val response = GetBookingInfoResponse(
                fareAmount = 230,
                fareCalculationInfo = "230 VND",
                minutesToDriverArrival = 5,
                driversNearbyLocation = listOf(),
            )

            Handler().postDelayed({
                it.bookingInfoResponse = response
            }, 2000)

//            bookingRepository.getBookingInfo(request, object : Callback<GetBookingInfoResponse> {
//                override fun onResponse(
//                    call: Call<GetBookingInfoResponse>,
//                    response: Response<GetBookingInfoResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.let { bookingInfoResponse ->
//                            it.bookingInfoResponse = bookingInfoResponse
//                        }
//                    } else {
//                        Toast.makeText(context, "Cannot get booking info", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<GetBookingInfoResponse>, t: Throwable) {
//                    Toast.makeText(context, "Cannot get booking info", Toast.LENGTH_SHORT).show()
//                }
//            })
        }
    }
}