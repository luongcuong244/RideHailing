package com.cuongnl.ridehailing.screens.home.tab.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.enums.Language
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordContinueButton
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordCreationDescription
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordCreationTitle
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordTextField
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordVisibilityButton
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.LocalStorageUtils
import com.cuongnl.ridehailing.viewmodel.AccountTabUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.NoRippleButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(
    accountTabUiViewModel: AccountTabUiViewModel = viewModel()
) {
    if (accountTabUiViewModel.isLanguageBottomSheetVisible.value) {

        val sheetState = rememberModalBottomSheetState()

        LaunchedEffect(accountTabUiViewModel.isLanguageBottomSheetVisible.value) {
            if (accountTabUiViewModel.isLanguageBottomSheetVisible.value) {
                sheetState.show()
            } else {
                sheetState.hide()
            }
        }

        ModalBottomSheet(
            onDismissRequest = {
                accountTabUiViewModel.setLanguageBottomSheetVisible(false)
            },
            sheetState = sheetState,
            windowInsets = WindowInsets.navigationBars,
            modifier = Modifier.navigationBarsPadding(),
            containerColor = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.sdp),
                verticalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Title()
                Description()
                Languages()
            }
        }
    }
}

@Composable
private fun Title() {
    AppText(
        text = stringResource(id = R.string.select_language),
        fontSize = 13.ssp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
private fun Description() {
    AppText(
        text = stringResource(id = R.string.set_your_app_display_language),
        fontSize = 11.ssp,
        color = Color.Black
    )
}

@Composable
private fun Languages() {
    LazyColumn(
        content = {
            items(Language.values().size, itemContent = { index ->
                val language = Language.values()[index]
                LanguageItem(language = language)
            })
        }
    )
}

@Composable
private fun LanguageItem(
    language: Language,
    accountTabUiViewModel: AccountTabUiViewModel = viewModel()
) {

    val context = LocalContext.current

    NoRippleButton(
        onClick = {
            LocalStorageUtils.writeData(context, LocalStorageUtils.KEY_LANGUAGE, language.languageCode)
            accountTabUiViewModel.resetApp(context)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Image(
                    modifier = Modifier
                        .size(18.sdp),
                    painter = painterResource(id = language.icon),
                    contentDescription = null
                )
                AppText(
                    text = language.languageText,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.sdp, bottom = 3.sdp),
                    fontSize = 13.ssp,
                    color = Color.Black,
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .height(1.sdp)
                .background(colorResource(id = R.color.gray_200))
            )
        }
    }
}