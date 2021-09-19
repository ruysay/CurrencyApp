package com.lccoding.currencyapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.lccoding.currencyapp.data.local.DbTest
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import com.lccoding.currencyapp.domain.use_case.get_currencies.GetCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.sort_currencies.SortCurrenciesUseCase
import com.lccoding.currencyapp.presentation.ui.curreny_list.components.CurrencyListState
import com.lccoding.currencyapp.presentation.ui.main.CurrencyViewModel
import com.lccoding.currencyapp.utils.getValue
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyViewModelTest : DbTest(){
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

//    @MockK
//    private lateinit var repository: CurrencyRepositoryImpl

    private val repository = mock(CurrencyRepository::class.java)

    @MockK
    private lateinit var currencyViewModel: CurrencyViewModel

    @MockK
    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @MockK
    private lateinit var sortCurrenciesUseCase: SortCurrenciesUseCase

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Mock
    private lateinit var viewStateObserver: Observer<CurrencyListState>

    @Before
    fun setUp() {
//        repository = CurrencyRepositoryImpl(db.getCurrencyDao(), context)
//        getCurrenciesUseCase = GetCurrenciesUseCase(repository)
//        sortCurrenciesUseCase = SortCurrenciesUseCase(repository)
//
//        MockitoAnnotations.initMocks(this)
//        currencyViewModel = CurrencyViewModel(
//            getCurrenciesUseCase,
//            sortCurrenciesUseCase
//        ).apply {
//            state.observeForever(viewStateObserver)
//        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSec() = runBlockingTest {
        currencyViewModel.getCurrencies()
        assertTrue(getValue(currencyViewModel.state).isLoading)
        verify(viewStateObserver).onChanged(CurrencyListState(isLoading = true))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun TestMinus() = runBlocking {
        currencyViewModel.getCurrencies()
        val state = getValue(currencyViewModel.state)
        Assert.assertTrue(state.currencies.isEmpty())
    }
}