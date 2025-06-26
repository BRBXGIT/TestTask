package com.example.currencyconverter.presentation.currency_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.currencyconverter.presentation.change_screen.ChangeScreenRoute

@Composable
fun CurrencyScreen(
    viewModel: CurrencyScreenVM = hiltViewModel<CurrencyScreenVM>(),
    navController: NavController
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            itemsIndexed(screenState.currentRates) { index, rate ->
                CurrencyItem(
                    currencyName = rate.currency,
                    currencyLabel = "Some label",
                    balance = "0",
                    amount = rate.value.toString().take(7),
                    onClick = {
                        viewModel.sendIntent(
                            CurrencyScreenIntent.UpdateScreenState(
                                screenState.copy(currentCurrencyCountryCode = it)
                            )
                        )
                    },
                    index = index,
                    onDone = {
                        viewModel.sendIntent(
                            CurrencyScreenIntent.UpdateScreenState(
                                screenState.copy(currentAmount = it)
                            )
                        )
                    },
                    onLongClick = {
                        navController.navigate(
                            ChangeScreenRoute(
                                firstCurrency = screenState.currentCurrencyCountryCode,
                                secondCurrency = rate.currency,
                                firstAmount = screenState.currentAmount,
                                secondAmount = rate.value
                            )
                        )
                    }
                )
            }
        }
    }
}