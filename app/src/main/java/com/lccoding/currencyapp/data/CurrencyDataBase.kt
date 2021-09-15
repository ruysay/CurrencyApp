package com.lccoding.currencyapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lccoding.currencyapp.data.dao.CurrencyDao
import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lccoding.currencyapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    private class CurrencyDatabaseCallback(
        private val scope: CoroutineScope,
        private val resources: Resources
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val currencyDao = database.getCurrencyDao()
                    prePopulateDatabase(currencyDao)
                }
            }
        }

        private suspend fun prePopulateDatabase(currencyDao: CurrencyDao) {
            val jsonString = resources.openRawResource(R.raw.currencies).bufferedReader().use {
                it.readText()
            }
            val typeToken = object : TypeToken<List<CurrencyEntity>>() {}.type
            val currencies = Gson().fromJson<List<CurrencyEntity>>(jsonString, typeToken)
            currencyDao.insertAllCurrencies(currencies)
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope, resources: Resources): CurrencyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currencies_database")
                    .addCallback(CurrencyDatabaseCallback(coroutineScope, resources))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}