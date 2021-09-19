package com.lccoding.currencyapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.lccoding.currencyapp.data.local.DbTest
import com.lccoding.currencyapp.domain.use_case.get_currencies.GetCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.sort_currencies.SortCurrenciesUseCase
import com.lccoding.currencyapp.presentation.ui.curreny_list.components.CurrencyListState
import com.lccoding.currencyapp.presentation.ui.main.CurrencyViewModel
import com.lccoding.currencyapp.repository.FakeCurrencyRepository
import com.lccoding.currencyapp.utils.getValue
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyViewModelTestSec : DbTest(){
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @MockK
    private lateinit var sortCurrenciesUseCase: SortCurrenciesUseCase

    @Mock
    private lateinit var viewStateObserver: Observer<CurrencyListState>

    private lateinit var currencyViewModel: CurrencyViewModel

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setup() {
        getCurrenciesUseCase = GetCurrenciesUseCase(FakeCurrencyRepository())
        sortCurrenciesUseCase = SortCurrenciesUseCase(FakeCurrencyRepository())
        currencyViewModel = CurrencyViewModel(getCurrenciesUseCase, sortCurrenciesUseCase)
        currencyViewModel = CurrencyViewModel(getCurrenciesUseCase, sortCurrenciesUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testcase1() = runBlocking {
        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        assertTrue(state.error.isEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testcase2() = runBlocking {
        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        Assert.assertTrue(state.currencies.isEmpty())
    }
}