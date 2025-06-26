package com.example.currencyconverter.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val currencyConverterDispatcher: CurrencyConverterDispatchers)

enum class CurrencyConverterDispatchers {
    IO
}