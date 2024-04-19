package com.cuongnl.ridehailing.screens.home.tab.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.screens.home.tab.history.ui.Bills
import com.cuongnl.ridehailing.screens.home.tab.history.ui.Header
import com.cuongnl.ridehailing.viewmodel.HistoryTabUiViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader

@Composable
fun HistoryTab(
    historyTabUiViewModel: HistoryTabUiViewModel = viewModel(),
    loaderViewModel: LoaderViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        loaderViewModel.setLoading(true)
        historyTabUiViewModel.fetchingBills() {
            loaderViewModel.setLoading(false)
        }
    }

    FullScreenLoader {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()
            Bills()
        }
    }
}