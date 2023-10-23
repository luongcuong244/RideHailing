package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.CountryCodeBottomSheetViewModel
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneCodeBottomSheet(countryCodeBottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {

        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .padding(start = 15.sdp, end = 15.sdp)
                .fillMaxWidth()
        ) {
            SelectCountryCodeText()
            SearchBar()
            CountryCodeList()
        }   
    }

    LaunchedEffect(countryCodeBottomSheetViewModel.isBottomSheetVisible.value){
        if(countryCodeBottomSheetViewModel.isBottomSheetVisible.value){
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }
}