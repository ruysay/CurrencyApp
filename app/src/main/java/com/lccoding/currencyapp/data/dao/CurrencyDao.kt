package com.lccoding.currencyapp.data.dao

import androidx.room.*
import com.lccoding.currencyapp.data.local.CurrencyInfo

@Dao
interface CurrencyDao {
    @Delete
    suspend fun deleteCurrency(currency: CurrencyInfo)

    @Update
    suspend fun updateCurrency(currency: CurrencyInfo)

    @Query("SELECT * FROM currencies WHERE id = :id")
    fun getCurrency(id: String): CurrencyInfo // was LiveData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencies(currencies: List<CurrencyInfo>)

    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): List<CurrencyInfo>

    @Query("SELECT * FROM currencies ORDER BY name ASC")
    suspend fun getSortedCurrencies(): List<CurrencyInfo>
}