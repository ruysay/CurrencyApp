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
import timber.log.Timber

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {

        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currencies_database")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                        }
                    }).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}