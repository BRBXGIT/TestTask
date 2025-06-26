package com.example.currencyconverter.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.currencyconverter.presentation.change_screen.ChangeScreen
import com.example.currencyconverter.presentation.change_screen.ChangeScreenRoute
import com.example.currencyconverter.presentation.currency_screen.CurrencyScreen
import com.example.currencyconverter.presentation.transactions_screen.TransactionsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        startDestination = "currencyScreen",
        navController = navController
    ) {
        composable(
            route = "currencyScreen"
        ) {
            CurrencyScreen(navController = navController)
        }

        composable<ChangeScreenRoute> {
            val firstCurrency = it.toRoute<ChangeScreenRoute>().firstCurrency
            val secondCurrency = it.toRoute<ChangeScreenRoute>().secondCurrency
            val firstAmount = it.toRoute<ChangeScreenRoute>().firstAmount
            val secondAmount = it.toRoute<ChangeScreenRoute>().secondAmount

            ChangeScreen(
                firstCurrency = firstCurrency,
                secondCurrency = secondCurrency,
                firstAmount = firstAmount,
                secondAmount = secondAmount,
                navController = navController
            )
        }

        composable(
            route = "transactionsScreen"
        ) {
            TransactionsScreen()
        }
    }
}