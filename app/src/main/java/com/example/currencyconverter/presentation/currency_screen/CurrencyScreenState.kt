package com.example.currencyconverter.presentation.currency_screen

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto

data class CurrencyScreenState(
    val currentCurrencyCountryCode: String = "RUB",
    val currentRates: List<RateDto> = emptyList(),
    val currentAmount: Double = 1.0
)