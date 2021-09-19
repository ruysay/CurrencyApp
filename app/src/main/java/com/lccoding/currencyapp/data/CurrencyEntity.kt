package com.lccoding.currencyapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.lccoding.currencyapp.domain.model.Currency
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "currencies")
@Parcelize
data class CurrencyEntity(
    @PrimaryKey
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("symbol") val symbol: String = ""
) : Parcelable

fun CurrencyEntity.toCurrency(): Currency {
    return Currency(
        id = id,
        name = name,
        symbol = symbol
    )
}