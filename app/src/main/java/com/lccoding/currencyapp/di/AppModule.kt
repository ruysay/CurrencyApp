package com.lccoding.currencyapp.di

import android.app.Application
import com.lccoding.currencyapp.data.CurrencyDatabase
import com.lccoding.currencyapp.data.repository.CurrencyRepositoryImpl
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //database instance

    @Provides
    @Singleton
    fun provideDataBase(app: Application): CurrencyDatabase {
        return CurrencyDatabase.getDatabase(app, CoroutineScope(Dispatchers.IO), app.resources)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(db: CurrencyDatabase): CurrencyRepository {
        return CurrencyRepositoryImpl(db.getCurrencyDao())
    }
}