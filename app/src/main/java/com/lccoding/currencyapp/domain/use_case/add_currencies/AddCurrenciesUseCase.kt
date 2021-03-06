package com.lccoding.currencyapp.domain.use_case.add_currencies

import com.lccoding.currencyapp.common.Resource
import com.lccoding.currencyapp.data.local.CurrencyInfo
import com.lccoding.currencyapp.data.local.toCurrency
import com.lccoding.currencyapp.domain.model.Currency
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(list: List<CurrencyInfo>): Flow<Resource<List<Currency>>> = flow {
        try {
            repository.addCurrencies(list)
            val currencies = list.map {
                it.toCurrency()
            }
            emit(Resource.Success<List<Currency>>(currencies))

        } catch (e: IOException) {
            emit(Resource.Error<List<Currency>>(e.localizedMessage ?: "Unexpected Error Occurred"))
        }
    }
}