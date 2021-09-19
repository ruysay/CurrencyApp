package com.lccoding.currencyapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.lccoding.currencyapp.data.local.DbTest
import com.lccoding.currencyapp.data.repository.CurrencyRepositoryImpl
import com.lccoding.currencyapp.domain.use_case.add_currencies.AddCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.get_currencies.GetCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.sort_currencies.SortCurrenciesUseCase
import com.lccoding.currencyapp.presentation.ui.curreny_list.CurrencyViewModel
import com.lccoding.currencyapp.repository.FakeCurrencyRepository
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyUseCaseTest : DbTest() {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var currencyViewModel: CurrencyViewModel

    @MockK
    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @MockK
    private lateinit var sortCurrenciesUseCase: SortCurrenciesUseCase

    @MockK
    private lateinit var addCurrenciesUseCase: AddCurrenciesUseCase

    @MockK
    private lateinit var repository: CurrencyRepositoryImpl

    @Before
    fun setUp() {
        repository = CurrencyRepositoryImpl(db.getCurrencyDao())
        getCurrenciesUseCase = GetCurrenciesUseCase(repository)
        sortCurrenciesUseCase = SortCurrenciesUseCase(repository)
        addCurrenciesUseCase = AddCurrenciesUseCase(FakeCurrencyRepository())

        currencyViewModel =
            CurrencyViewModel(getCurrenciesUseCase, sortCurrenciesUseCase, addCurrenciesUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testUseCaseGivesEmptyMessage() = runBlocking {
        val firstItem = getCurrenciesUseCase.invoke().first()
        assertTrue(firstItem.message == null)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun useCaseGivesNotEmptyList() = runBlocking {
        val secondItem = getCurrenciesUseCase.invoke().drop(1).first()
        assertTrue(secondItem.data?.isEmpty() == true)
    }
}