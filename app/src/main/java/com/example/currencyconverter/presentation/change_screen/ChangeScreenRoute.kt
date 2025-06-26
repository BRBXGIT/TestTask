package com.example.currencyconverter.presentation.change_screen

import kotlinx.serialization.Serializable

@Serializable
data class ChangeScreenRoute(
    val firstCurrency: String,
    val secondCurrency: String,
    val firstAmount: Double,
    val secondAmount: Double,
)