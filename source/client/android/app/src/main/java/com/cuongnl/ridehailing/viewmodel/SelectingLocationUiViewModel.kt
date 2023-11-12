package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse

class SelectingLocationUiViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "SelectingLocationUiViewModel"
    private val placesClient = Places.createClient(application)
    private var cancellationTokenSource = CancellationTokenSource()

    init {
        getAddressPredictions("Canh")
    }

    fun getAddressPredictions(query: String) {

        cancellationTokenSource.cancel()
        cancellationTokenSource = CancellationTokenSource()

        val request =
            FindAutocompletePredictionsRequest.builder()
                .setOrigin(LatLng(-33.8749937, 151.2041382))
                .setCountries("VN")
                .setTypesFilter(listOf(PlaceTypes.ADDRESS))
                .setCancellationToken(cancellationTokenSource.token)
                .setQuery(query)
                .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                for (prediction in response.autocompletePredictions) {
                    Log.i(TAG, prediction.placeId)
                    Log.i(TAG, prediction.getPrimaryText(null).toString())
                }
            }.addOnFailureListener { exception: Exception? ->
                if (exception is ApiException) {
                    Log.e(TAG, "Place not found: ${exception.statusCode}")
                }
            }
    }

}