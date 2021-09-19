package com.lccoding.currencyapp.domain.repository

import com.lccoding.currencyapp.data.local.CurrencyInfo

interface CurrencyRepository {
    suspend fun getCurrencies(): List<CurrencyInfo>

    suspend fun getSortedCurrencies(): List<CurrencyInfo>

    suspend fun addCurrencies(list: List<CurrencyInfo>)
}