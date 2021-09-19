package com.lccoding.currencyapp.domain.use_case.get_currencies

import com.lccoding.currencyapp.common.Resource
import com.lccoding.currencyapp.data.toCurrency
import com.lccoding.currencyapp.domain.model.Currency
import com.lccoding.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(): Flow<Resource<List<Currency>>> = flow {
        try {
            emit(Resource.Loading<List<Currency>>())
            val currencies = repository.getCurrencies().map { it.toCurrency() }
            emit(Resource.Success<List<Currency>>(currencies))

        } catch (e: IOException) {
            emit(Resource.Error<List<Currency>>(e.localizedMessage ?: "Unexpected Error Occurred"))
        }
    }
}