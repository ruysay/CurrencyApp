package com.lccoding.currencyapp.di

import android.app.Application
import com.lccoding.currencyapp.common.SharedPreferenceUtil
import com.lccoding.currencyapp.data.local.CurrencyDatabase
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

    @Provides
    @Singleton
    fun provideDataBase(app: Application): CurrencyDatabase {
        return CurrencyDatabase.getDatabase(app)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(db: CurrencyDatabase): CurrencyRepository {
        return CurrencyRepositoryImpl(db.getCurrencyDao())
    }

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Provides
    @Singleton
    fun provideSharedPreferenceUtil(app: Application): SharedPreferenceUtil {
        return SharedPreferenceUtil(app)
    }
}