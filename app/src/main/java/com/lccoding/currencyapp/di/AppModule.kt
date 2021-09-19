package com.lccoding.currencyapp.di

import android.app.Application
import android.content.Context
import com.lccoding.currencyapp.data.CurrencyDatabase
import com.lccoding.currencyapp.data.repository.CurrencyRepositoryImpl
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //database instance

    @Provides
    @Singleton
    fun provideDataBase(app: Application): CurrencyDatabase {
        return CurrencyDatabase.getDatabase(app)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(db: CurrencyDatabase, app: Application): CurrencyRepository {
        // the application instance is used for prepopulating DB, we don't really need to inject it
        return CurrencyRepositoryImpl(db.getCurrencyDao(), app)
    }

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)
}