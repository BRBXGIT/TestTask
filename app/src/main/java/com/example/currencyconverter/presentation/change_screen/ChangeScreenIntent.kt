package com.example.currencyconverter.presentation.change_screen

import java.time.LocalDateTime

sealed class ChangeScreenIntent {
    data class InsertTransaction(
        val from: String,
        val to: String,
        val fromAmount: Double,
        val toAmount: Double,
        val dateTime: LocalDateTime
    ): ChangeScreenIntent()
}