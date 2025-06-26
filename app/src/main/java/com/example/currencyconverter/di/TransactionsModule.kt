package com.example.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.data.dataSource.room.ConverterDatabase
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionsModule {

    @Provides
    @Singleton
    fun provideTransactionsDao(
        @ApplicationContext context: Context
    ): TransactionDao {
        return Room.databaseBuilder(
            context = context,
            klass = ConverterDatabase::class.java,
            name = "converters_db"
        ).build().transactionDao()
    }
}