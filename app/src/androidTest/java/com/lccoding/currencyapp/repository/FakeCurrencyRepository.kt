package com.lccoding.currencyapp.repository

import com.lccoding.currencyapp.data.local.CurrencyInfo
import com.lccoding.currencyapp.domain.repository.CurrencyRepository

class FakeCurrencyRepository : CurrencyRepository {
    private val currencyItems = mutableListOf<CurrencyInfo>()

    override suspend fun getCurrencies(): List<CurrencyInfo> {
        return currencyItems
    }

    override suspend fun getSortedCurrencies(): List<CurrencyInfo> {
        return currencyItems
    }

    override suspend fun addCurrencies(list: List<CurrencyInfo>) {
        currencyItems.addAll(list)
    }
}