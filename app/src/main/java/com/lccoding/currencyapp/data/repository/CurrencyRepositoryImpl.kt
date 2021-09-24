package com.lccoding.currencyapp.data.repository

import com.lccoding.currencyapp.data.local.CurrencyInfo
import com.lccoding.currencyapp.data.dao.CurrencyDao
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val dao: CurrencyDao,
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<CurrencyInfo> = withContext(Dispatchers.IO) {
        return@withContext dao.getAllCurrencies()
    }

    override suspend fun getSortedCurrencies(): List<CurrencyInfo> = withContext(Dispatchers.IO) {
            return@withContext dao.getSortedCurrencies()
        }

    override suspend fun addCurrencies(list: List<CurrencyInfo>) = withContext(Dispatchers.IO) {
        dao.insertAllCurrencies(list)
    }
}