package com.lccoding.currencyapp.domain.repository

import androidx.lifecycle.LiveData
import com.lccoding.currencyapp.data.CurrencyListItem

interface CurrencyRepository {
    suspend fun getCurrencies(): LiveData<List<CurrencyListItem>>

    suspend fun getSortedCurrencies(): LiveData<List<CurrencyListItem>>
}