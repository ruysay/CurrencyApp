package com.lccoding.currencyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lccoding.currencyapp.data.local.CurrencyInfo
import com.lccoding.currencyapp.domain.use_case.add_currencies.AddCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.get_currencies.GetCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.sort_currencies.SortCurrenciesUseCase
import com.lccoding.currencyapp.presentation.ui.curreny_list.components.CurrencyListState
import com.lccoding.currencyapp.presentation.ui.curreny_list.CurrencyViewModel
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @MockK
    private lateinit var sortCurrenciesUseCase: SortCurrenciesUseCase

    @MockK
    private lateinit var addCurrenciesUseCase: AddCurrenciesUseCase

    private lateinit var currencyViewModel: CurrencyViewModel

    @Mock
    private lateinit var viewStateObserver: Observer<CurrencyListState>

    @Before
    fun setup() {
        getCurrenciesUseCase = GetCurrenciesUseCase(FakeCurrencyRepository())
        sortCurrenciesUseCase = SortCurrenciesUseCase(FakeCurrencyRepository())
        addCurrenciesUseCase = AddCurrenciesUseCase(FakeCurrencyRepository())

        currencyViewModel = CurrencyViewModel(
            getCurrenciesUseCase,
            sortCurrenciesUseCase,
            addCurrenciesUseCase
        ).apply {
            state.observeForever(viewStateObserver)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCurrencyWithNoError() = runBlocking {
        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        assertTrue(state.error.isEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCurrencyDefaultResult() = runBlocking {
        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        Assert.assertTrue(state.currencies.isEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCurrencyAndObserveIsLoading() = runBlocking {
        currencyViewModel.getCurrencies()
        assertTrue(getValue(currencyViewModel.state).isLoading)
        Mockito.verify(viewStateObserver).onChanged(CurrencyListState(isLoading = true))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCurrencyAndObserveResultIsEmpty() = runBlocking {
        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        Assert.assertTrue(state.currencies.isEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addCurrencyAndObserveResultIsNotEmpty() = runBlocking {
        val setA = CurrencyInfo(id = "abc", name = "123", symbol = "abc123")
        val setB = CurrencyInfo(id = "def", name = "456", symbol = "def456")
        val list = listOf(setA, setB)

        currencyViewModel.addCurrencies(list)
//        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        Assert.assertTrue(state.currencies.isNotEmpty())
    }
}