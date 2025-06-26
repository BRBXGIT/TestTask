package com.example.currencyconverter.presentation.transactions_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TransactionsScreenVM @Inject constructor(
    transactionDao: TransactionDao
): ViewModel() {
    val transactions = transactionDao.getAll().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )
}