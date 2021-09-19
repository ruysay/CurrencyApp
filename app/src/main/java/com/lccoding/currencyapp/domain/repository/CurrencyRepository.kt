package com.lccoding.currencyapp.domain.repository

import com.lccoding.currencyapp.data.CurrencyEntity

interface CurrencyRepository {
    suspend fun getCurrencies(): List<CurrencyEntity>

    suspend fun getSortedCurrencies(): List<CurrencyEntity>
}