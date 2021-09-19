package com.lccoding.currencyapp.data.repository

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lccoding.currencyapp.R
import com.lccoding.currencyapp.data.local.CurrencyEntity
import com.lccoding.currencyapp.data.dao.CurrencyDao
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val dao: CurrencyDao,
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<CurrencyEntity> = withContext(Dispatchers.IO) {
        return@withContext dao.getAllCurrencies()
    }

    override suspend fun getSortedCurrencies(): List<CurrencyEntity> =
        withContext(Dispatchers.IO) {
            return@withContext dao.getSortedCurrencies()
        }

    override suspend fun addCurrencies(list: List<CurrencyEntity>) = withContext(Dispatchers.IO) {
        dao.insertAllCurrencies(list)
    }
}