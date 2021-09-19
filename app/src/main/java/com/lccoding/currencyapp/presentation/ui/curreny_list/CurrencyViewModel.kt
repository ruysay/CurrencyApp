package com.lccoding.currencyapp.presentation.ui.curreny_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lccoding.currencyapp.common.Resource
import com.lccoding.currencyapp.domain.model.Currency
import com.lccoding.currencyapp.domain.use_case.get_currencies.GetCurrenciesUseCase
import com.lccoding.currencyapp.domain.use_case.sort_currencies.SortCurrenciesUseCase
import com.lccoding.currencyapp.presentation.ui.curreny_list.components.CurrencyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//part of business logics have been moved to domain layer, so view model will do less business logic than before
@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val sortCurrenciesUseCase: SortCurrenciesUseCase
) : ViewModel() {

    private val _state =
        MutableLiveData<CurrencyListState>() // this will not be accessed by external
    val state: LiveData<CurrencyListState> = _state // live data for view to observe and react
    val selectedItem = MutableLiveData<Currency>()

    fun getCurrencies() {
        getCurrenciesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrencyListState(currencies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CurrencyListState(
                        error = result.message ?: "Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrencyListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSortedCurrencies() {
        sortCurrenciesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrencyListState(currencies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CurrencyListState(
                        error = result.message ?: "Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrencyListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun selectItem(item: Currency) {
        selectedItem.value = item
    }
}