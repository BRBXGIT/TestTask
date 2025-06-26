package com.example.currencyconverter.presentation.currency_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.remote.RatesService
import com.example.currencyconverter.di.CurrencyConverterDispatchers
import com.example.currencyconverter.di.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyScreenVM @Inject constructor(
    private val ratesService: RatesService,
    @Dispatcher(CurrencyConverterDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _screenState = MutableStateFlow(CurrencyScreenState())
    val screenState = _screenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        CurrencyScreenState()
    )

    private fun updateScreenState(state: CurrencyScreenState) {
        _screenState.value = state
    }

    private fun getRates() {
        viewModelScope.launch(dispatcherIo) {
            updateScreenState(
                _screenState.value.copy(
                    currentRates = ratesService.getRates(
                        baseCurrencyCode = _screenState.value.currentCurrencyCountryCode,
                        amount = _screenState.value.currentAmount
                    )
                )
            )
            delay(1_000)
            getRates()
        }
    }

    fun sendIntent(intent: CurrencyScreenIntent) {
        when (intent) {
            is CurrencyScreenIntent.UpdateScreenState -> updateScreenState(intent.screenState)
        }
    }

    init {
        getRates()
    }
}