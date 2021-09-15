package com.lccoding.currencyapp.presentation.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lccoding.currencyapp.common.Resource
import com.lccoding.currencyapp.data.CurrencyEntity
import com.lccoding.currencyapp.data.CurrencyListItem
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import com.lccoding.currencyapp.domain.use_case.get_currencies.GetCurrenciesUseCase
import com.lccoding.currencyapp.presentation.ui.curreny_list.components.CurrencyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//part of business logics have been moved to domain layer, so view model will do less business logic than before
@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
//    private val repository: CurrencyRepository,
//    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    private val _state = mutableStateOf(CurrencyListState())
    val state: State<CurrencyListState> = _state

    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        getCurrenciesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CurrencyListState(currencies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CurrencyListState(error =  result.message ?:
                    "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CurrencyListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

//    private var currencies: Resource<CurrencyEntity>? = null
//
//    suspend fun getCurrencies() : List<CurrencyListItem> {
//        return repository.getCurrencies()
//    }
//
//    suspend fun getSortedCurrencies() : List<CurrencyListItem> {
//        return repository.getSortedCurrencies()
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        ioCoroutineScope.cancel()
//    }
}