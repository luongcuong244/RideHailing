package com.cuongnl.ridehailing.screens.home.tab.history.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.viewmodel.HistoryTabUiViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.models.Bill
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ColumnScope.Bills(
    historyTabUiViewModel: HistoryTabUiViewModel = viewModel(),
    loaderViewModel: LoaderViewModel = viewModel()
) {
    if (!loaderViewModel.isLoading.value) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            if (historyTabUiViewModel.bills.isEmpty()) {
                EmptyView()
            } else {
                BillsList()
            }
        }
    }
}

@Composable
private fun BoxScope.EmptyView(
    historyTabUiViewModel: HistoryTabUiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .align(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_history_empty),
            contentDescription = null,
            modifier = Modifier
                .width(100.sdp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun BillsList(
    historyTabUiViewModel: HistoryTabUiViewModel = viewModel()
) {
    LazyColumn(
        content = {
            items(historyTabUiViewModel.bills.size) { index ->
                BillItem(bill = historyTabUiViewModel.bills[index])
            }
        }
    )
}

@Composable
private fun BillItem(bill: Bill) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppText(text = bill.pickupAddress, modifier = Modifier)
    }
}