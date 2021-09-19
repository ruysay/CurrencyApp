package com.lccoding.currencyapp.data.repository

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lccoding.currencyapp.R
import com.lccoding.currencyapp.data.CurrencyEntity
import com.lccoding.currencyapp.data.dao.CurrencyDao
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val dao: CurrencyDao,
    private val context: Context
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<CurrencyEntity> = withContext(Dispatchers.IO) {
        //read from raw data if we don't have anything in db
        if (dao.getAllCurrencies().isNullOrEmpty()) {
            prePopulateDatabase(dao, resources = context.resources)
        }
        return@withContext dao.getAllCurrencies()
    }

    override suspend fun getSortedCurrencies(): List<CurrencyEntity> =
        withContext(Dispatchers.IO) {
            return@withContext dao.getSortedCurrencies()
        }

    private suspend fun prePopulateDatabase(currencyDao: CurrencyDao, resources: Resources) {
        val jsonString = resources.openRawResource(R.raw.currencies).bufferedReader().use {
            it.readText()
        }
        val typeToken = object : TypeToken<List<CurrencyEntity>>() {}.type
        val currencies = Gson().fromJson<List<CurrencyEntity>>(jsonString, typeToken)
        currencyDao.insertAllCurrencies(currencies)
    }
}