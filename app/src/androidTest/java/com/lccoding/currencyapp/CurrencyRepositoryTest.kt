package com.lccoding.currencyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lccoding.currencyapp.data.local.CurrencyDatabase
import com.lccoding.currencyapp.data.dao.CurrencyDao
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class CurrencyRepositoryTest {
    private val dao = mock(CurrencyDao::class.java)
    private val repository = mock(CurrencyRepository::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        val db = mock(CurrencyDatabase::class.java)
        Mockito.`when`(db.getCurrencyDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
    }

    @Test
    fun loadCurrenciesFromDb() {
        runBlocking {
            repository.getCurrencies()

            Mockito.verify(dao, Mockito.never()).getAllCurrencies()
            Mockito.verifyZeroInteractions(dao)
        }
    }
}