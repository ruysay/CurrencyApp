package com.lccoding.currencyapp.data.repository

import androidx.lifecycle.LiveData
import com.lccoding.currencyapp.data.CurrencyListItem
import com.lccoding.currencyapp.data.dao.CurrencyDao
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val dao: CurrencyDao
) : CurrencyRepository{
    override suspend fun getCurrencies(): LiveData<List<CurrencyListItem>> {
        return dao.getAllCurrencies()
    }

    override suspend fun getSortedCurrencies(): LiveData<List<CurrencyListItem>> {
        return dao.getSortedCurrencies()
    }
}