package com.example.currencyconverter.presentation.change_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.currencyconverter.presentation.currency_screen.CurrencyItem
import java.time.LocalDateTime

@Composable
fun ChangeScreen(
    firstCurrency: String,
    secondCurrency: String,
    firstAmount: Double,
    secondAmount: Double,
    viewModel: ChangeScreenVM = hiltViewModel<ChangeScreenVM>(),
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffFFFFFF))
                .padding(innerPadding)
        ) {
            Text(
                text = "$firstCurrency to $secondCurrency",
            )

            CurrencyItem(
                currencyName = firstCurrency,
                currencyLabel = "Some label",
                amount = firstAmount.toString()
            )

            CurrencyItem(
                currencyName = secondCurrency,
                currencyLabel = "Some label",
                amount = secondAmount.toString()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.sendIntent(
                        ChangeScreenIntent.InsertTransaction(
                            from = firstCurrency,
                            to = secondCurrency,
                            fromAmount = firstAmount,
                            toAmount = secondAmount,
                            dateTime = LocalDateTime.now()
                        )
                    )
                    navController.navigate("transactionsScreen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffe7f1fc),
                    contentColor = Color(0xff000000)
                )
            ) {
                Text(
                    text = "Buy $firstCurrency for $secondCurrency"
                )
            }
        }
    }
}