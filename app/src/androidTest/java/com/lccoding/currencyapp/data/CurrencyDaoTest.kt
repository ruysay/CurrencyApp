package com.lccoding.currencyapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lccoding.currencyapp.data.dao.CurrencyDao
import com.lccoding.currencyapp.utils.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertThat

@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest : DbTest() {
    private lateinit var currencyDao: CurrencyDao

    private val setA = CurrencyEntity(id = "abc", name = "123", symbol = "abc123")
    private val setB = CurrencyEntity(id = "def", name = "456", symbol = "def456")

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

    @Test
    fun testGetSets() {
        val list = getValue(currencyDao.getAllCurrencies())
        assertThat(list.size, Matchers.equalTo(2))
        assertThat(list[0].id, Matchers.equalTo(setA.id))
        assertThat(list[1].id, Matchers.equalTo(setB.id))
    }
}