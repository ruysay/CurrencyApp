package com.lccoding.currencyapp.domain.repository

import com.lccoding.currencyapp.data.local.CurrencyEntity

interface CurrencyRepository {
    suspend fun getCurrencies(): List<CurrencyEntity>

    suspend fun getSortedCurrencies(): List<CurrencyEntity>

    suspend fun addCurrencies(list: List<CurrencyEntity>)
}