package com.lccoding.currencyapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.lccoding.currencyapp.domain.model.Currency
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyListItem(
    @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "symbol") val symbol: String = ""
) : Parcelable

fun CurrencyListItem.toCurrency(): Currency {
    return Currency(
        id = id,
        name = name,
        symbol = symbol
    )
}