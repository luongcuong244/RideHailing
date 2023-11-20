package com.cuongnl.ridehailing.viewmodel

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.enums.PaymentMethod
import com.cuongnl.ridehailing.enums.TransportationType
import com.cuongnl.ridehailing.extensions.findActivity
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.models.api.GetBookingInfoRequest
import com.cuongnl.ridehailing.models.api.GetBookingInfoResponse
import com.cuongnl.ridehailing.models.item.RideBookingInfoItem
import com.cuongnl.ridehailing.retrofit.repository.BookingRepository
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode

class BookingActivityUiViewModel : ViewModel() {

    private val bookingRepository = BookingRepository()

    val points = mutableStateListOf<LatLng>()

    val bookingsInfo = mutableStateListOf<RideBookingInfoItem>()
    private var _selectedBookingIndex = mutableStateOf(0)

    private val _destinationLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _pickupLocationLatLng = mutableStateOf<LatLng>(CurrentLocation.getLatLng())
    private val _destinationLocationAddress = mutableStateOf("")
    private val _pickupLocationAddress = mutableStateOf("")
    private val _paymentMethod = mutableStateOf(PaymentMethod.CASH)
    private val _noteForDriver = mutableStateOf("")
    private val _isBottomSheetVisible = mutableStateOf(false)
    private val _fareCalculationInfoSelectedIndex = mutableStateOf(0)

    val selectedBookingIndex: State<Int> = _selectedBookingIndex
    val destinationLocationLatLng: State<LatLng> = _destinationLocationLatLng
    val pickupLocationLatLng: State<LatLng> = _pickupLocationLatLng
    val destinationLocationAddress: State<String> = _destinationLocationAddress
    val pickupLocationAddress: State<String> = _pickupLocationAddress
    val paymentMethod: State<PaymentMethod> = _paymentMethod
    val noteForDriver: State<String> = _noteForDriver
    val isBottomSheetVisible: State<Boolean> = _isBottomSheetVisible
    val fareCalculationInfoSelectedIndex: State<Int> = _fareCalculationInfoSelectedIndex

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

        val size = bookingsInfo.size

        for (i in 0 until size) {
            bookingsInfo[i].isSelected.value =
                bookingsInfo[i].transportationType == transportationType

            if (bookingsInfo[i].isSelected.value) {

                _selectedBookingIndex.value = i

                if (bookingsInfo[i].directionPoints == null) {
                    getDirectionsBetweenTwoPoints(context, transportationType.travelMode) {

                        val points = mutableListOf<LatLng>()

                        it.routes[0].legs[0].steps.forEach {
                            points.addAll(it.polyline.decodePath().map { latLng ->
                                val newLat = LatLng(latLng.lat, latLng.lng)
                                newLat
                            })
                        }

                        bookingsInfo[i].travelTimeInMinutes = it.routes[0].legs[0].duration.inSeconds / 60
                        bookingsInfo[i].directionPoints = points
                        setPoints(bookingsInfo[i].directionPoints!!)
                    }
                } else {
                    setPoints(bookingsInfo[i].directionPoints!!)
                }
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

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        _paymentMethod.value = paymentMethod
    }

    fun setNoteForDriver(noteForDriver: String) {
        _noteForDriver.value = noteForDriver
    }

    fun setIsBottomSheetVisible(isBottomSheetVisible: Boolean) {
        _isBottomSheetVisible.value = isBottomSheetVisible
    }

    fun setFareCalculationInfoSelectedIndex(fareCalculationInfoSelectedIndex: Int) {
        _fareCalculationInfoSelectedIndex.value = fareCalculationInfoSelectedIndex
    }

    private fun getDirectionsBetweenTwoPoints(
        context: Context,
        travelMode: TravelMode,
        onSuccess: (DirectionsResult) -> Unit = {},
    ) {

        MapUtils.getDirectionsBetweenTwoPoints(
            destinationLocationLatLng.value,
            pickupLocationLatLng.value,
            travelMode,
            object : com.google.maps.PendingResult.Callback<DirectionsResult> {
                override fun onResult(result: DirectionsResult?) {
                    if (result != null && result.routes.isNotEmpty()) {
                        onSuccess(result)
                    } else {
                        Toast.makeText(context, "Cannot get directions", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(e: Throwable?) {
                    context.findActivity()?.runOnUiThread {
                        Toast.makeText(context, "${e?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
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
                fareCalculationInfo = "<b>Hello</b> <i>World</i>",
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