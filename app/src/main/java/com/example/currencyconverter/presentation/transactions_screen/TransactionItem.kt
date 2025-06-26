package com.example.currencyconverter.presentation.transactions_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TransactionItem(
    from: String,
    fromAmount: Double,
    to: String,
    toAmount: Double,
    date: LocalDateTime
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = from,
                    color = Color(0xff000000)
                )

                Text(
                    text = fromAmount.toString().take(7),
                    color = Color(0xff000000)
                )
            }

            Text(
                text = "TO",
                color = Color(0xff000000)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = to,
                    color = Color(0xff000000)
                )

                Text(
                    text = toAmount.toString().take(7),
                    color = Color(0xff000000)
                )
            }
        }

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val formatted = date.format(formatter)
        Text(
            text = formatted,
            color = Color(0xff000000)
        )
    }
}