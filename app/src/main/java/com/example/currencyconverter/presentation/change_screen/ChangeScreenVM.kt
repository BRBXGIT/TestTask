package com.example.currencyconverter.presentation.change_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.di.CurrencyConverterDispatchers
import com.example.currencyconverter.di.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ChangeScreenVM @Inject constructor(
    private val transactionDao: TransactionDao,
    @Dispatcher(CurrencyConverterDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private fun insertTransaction(
        from: String,
        to: String,
        fromAmount: Double,
        toAmount: Double,
        dateTime: LocalDateTime
    ) {
        viewModelScope.launch(dispatcherIo) {
            transactionDao.insertAll(
                transactions = arrayOf(
                    TransactionDbo(
                        from = from,
                        to = to,
                        fromAmount = fromAmount,
                        toAmount = toAmount,
                        dateTime = dateTime
                    )
                )
            )
        }
    }

    fun sendIntent(intent: ChangeScreenIntent) {
        when (intent) {
            is ChangeScreenIntent.InsertTransaction -> {
                insertTransaction(
                    intent.from,
                    intent.to,
                    intent.fromAmount,
                    intent.toAmount,
                    intent.dateTime
                )
            }
        }
    }
}