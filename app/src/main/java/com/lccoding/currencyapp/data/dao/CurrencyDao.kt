package com.lccoding.currencyapp.data.dao

import androidx.room.*
import com.lccoding.currencyapp.data.CurrencyEntity

@Dao
interface CurrencyDao {
    @Delete
    suspend fun deleteCurrency(currency: CurrencyEntity)

    @Update
    suspend fun updateCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currencies WHERE id = :id")
    fun getCurrency(id: String): CurrencyEntity // was LiveData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): List<CurrencyEntity>

    @Query("SELECT * FROM currencies ORDER BY name ASC")
    suspend fun getSortedCurrencies(): List<CurrencyEntity>
}