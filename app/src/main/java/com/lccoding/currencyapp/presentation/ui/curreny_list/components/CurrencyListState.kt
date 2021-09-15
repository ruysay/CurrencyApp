package com.lccoding.currencyapp.presentation.ui.curreny_list.components

import com.lccoding.currencyapp.domain.model.Currency

data class CurrencyListState(
    val isLoading: Boolean = false,
    val currencies: List<Currency> = emptyList(),
    val error: String = ""
)
