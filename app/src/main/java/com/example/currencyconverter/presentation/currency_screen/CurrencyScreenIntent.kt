package com.example.currencyconverter.presentation.currency_screen

sealed class CurrencyScreenIntent {
    data class UpdateScreenState(val screenState: CurrencyScreenState): CurrencyScreenIntent()
}