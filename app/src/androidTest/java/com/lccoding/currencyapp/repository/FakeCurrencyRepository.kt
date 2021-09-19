package com.lccoding.currencyapp.repository

import com.lccoding.currencyapp.data.local.CurrencyEntity
import com.lccoding.currencyapp.domain.repository.CurrencyRepository

class FakeCurrencyRepository : CurrencyRepository {
    private val currencyItems = mutableListOf<CurrencyEntity>()

    override suspend fun getCurrencies(): List<CurrencyEntity> {
        return currencyItems
    }

    override suspend fun getSortedCurrencies(): List<CurrencyEntity> {
        return currencyItems
    }

    override suspend fun addCurrencies(list: List<CurrencyEntity>) {
        currencyItems.addAll(list)
    }
}