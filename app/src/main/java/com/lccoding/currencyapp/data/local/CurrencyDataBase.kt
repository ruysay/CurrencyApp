package com.lccoding.currencyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lccoding.currencyapp.data.dao.CurrencyDao
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
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
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currencies_database"
                )
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