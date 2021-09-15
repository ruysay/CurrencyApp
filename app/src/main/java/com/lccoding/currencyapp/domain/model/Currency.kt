package com.lccoding.currencyapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    val id: String,
    val name: String,
    val symbol: String
) : Parcelable