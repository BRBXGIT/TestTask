package com.example.currencyconverter.presentation.currency_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrencyItem(
    currencyName: String,
    currencyLabel: String,
    balance: String? = null,
    amount: String,
    onClick: (String) -> Unit = {},
    onDone: (Double) -> Unit = {},
    onLongClick: () -> Unit = {},
    index: Int? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable (
                onClick = { onClick(currencyName) },
                onLongClick = { onLongClick() }
            )
            .background(Color(0xFFecf5fe))
            .padding(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = currencyName,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )

            Text(
                text = currencyLabel,
                color = Color(0xFF000000)
            )

            if (balance != null) {
                Text(
                    text = balance,
                    color = Color(0xFF000000)
                )
            }
        }

        if (index == 0) {
            var newAmount by rememberSaveable { mutableStateOf(amount) }
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current
            TextField(
                trailingIcon = {
                    if (newAmount != "1.0") {
                        IconButton(
                            onClick = {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                onDone(1.0)
                                newAmount = "1.0"
                            }
                        ) {
                            Text(
                                text = "X",
                                color = Color(0xff000000)
                            )
                        }
                    }
                },
                textStyle = TextStyle(textAlign = TextAlign.End),
                        colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xff000000),
                    unfocusedTextColor = Color(0xff000000),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                maxLines = 1,
                value = newAmount,
                onValueChange = {
                    newAmount = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onDone(newAmount.toDouble())
                    }
                )
            )
        } else {
            Text(
                text = amount,
                color = Color(0xFF000000)
            )
        }
    }
}