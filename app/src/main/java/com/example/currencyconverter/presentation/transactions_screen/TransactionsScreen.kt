package com.example.currencyconverter.presentation.transactions_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TransactionsScreen(
    viewModel: TransactionsScreenVM = hiltViewModel<TransactionsScreenVM>()
) {
    val transactions = viewModel.transactions.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffFFFFFF))
                .padding(innerPadding)
        ) {
            items(transactions) { transaction ->
                TransactionItem(
                    from = transaction.from,
                    to = transaction.to,
                    fromAmount = transaction.fromAmount,
                    toAmount = transaction.toAmount,
                    date = transaction.dateTime
                )
            }
        }
    }
}