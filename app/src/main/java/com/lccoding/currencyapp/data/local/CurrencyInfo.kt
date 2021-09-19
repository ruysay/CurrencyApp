package com.lccoding.currencyapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.lccoding.currencyapp.domain.model.Currency
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "currencies")
@Parcelize
data class CurrencyInfo(
    @PrimaryKey
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("symbol") val symbol: String = ""
) : Parcelable

fun CurrencyInfo.toCurrency(): Currency {
    return Currency(
        id = id,
        name = name,
        symbol = symbol
    )
}