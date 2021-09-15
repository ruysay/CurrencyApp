package com.lccoding.currencyapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lccoding.currencyapp.data.CurrencyEntity
import com.lccoding.currencyapp.data.CurrencyListItem

@Dao
interface CurrencyDao {
    @Delete
    suspend fun deleteCurrency(currency: CurrencyEntity)

    @Update
    suspend fun updateCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currencies WHERE id = :id")
    fun getCurrency(id: String): CurrencyEntity // was LiveData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): LiveData<List<CurrencyListItem>>

    @Query("SELECT * FROM currencies ORDER BY name ASC")
    fun getSortedCurrencies(): LiveData<List<CurrencyListItem>>
}