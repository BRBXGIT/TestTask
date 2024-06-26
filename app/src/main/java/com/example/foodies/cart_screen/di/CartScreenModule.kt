package com.example.foodies.cart_screen.di

import android.content.Context
import androidx.room.Room
import com.example.foodies.cart_screen.data.db.CartDao
import com.example.foodies.cart_screen.data.db.CartDb
import com.example.foodies.cart_screen.data.repository.CartRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartScreenModule {

    //Provide cart dao
    @Provides
    @Singleton
    fun provideCartDao(@ApplicationContext appContext: Context): CartDao {
        return Room.databaseBuilder(
            appContext,
            CartDb::class.java,
            "CartDb"
        ).build().cartDao()
    }

    //Provide repository implementation
    @Provides
    @Singleton
    fun provideCartRepositoryImpl(cartDao: CartDao): CartRepositoryImpl {
        return CartRepositoryImpl(cartDao)
    }
}