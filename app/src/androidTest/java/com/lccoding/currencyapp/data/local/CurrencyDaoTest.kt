package com.lccoding.currencyapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.lccoding.currencyapp.data.dao.CurrencyDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest : DbTest() {
    private lateinit var currencyDao: CurrencyDao

    private val setA = CurrencyInfo(id = "abc", name = "123", symbol = "abc123")
    private val setB = CurrencyInfo(id = "def", name = "456", symbol = "def456")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        currencyDao = db.getCurrencyDao()

        // Insert legoSets in non-alphabetical order to test that results are sorted by name
        runBlocking {
            currencyDao.insertAllCurrencies(listOf(setA, setB))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetSets() = runBlockingTest{
        val list = currencyDao.getAllCurrencies()
        assertThat(list.size).isEqualTo(2)
        assertThat(list[0].id).isEqualTo(setA.id)
        assertThat(list[1].id).isEqualTo(setB.id)
    }
}