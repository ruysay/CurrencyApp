package com.lccoding.currencyapp.common

import android.app.Application
import android.content.Context
import javax.inject.Inject

class SharedPreferenceUtil @Inject constructor(app: Application
) {

    private val systemSharedPreferences = app.getSharedPreferences("system", Context.MODE_PRIVATE)

    /**
     */
    fun isFirstLaunch(): Boolean {
        return systemSharedPreferences.getBoolean(IS_FIRST_LAUNCH, true)
    }

    fun setFirstLaunch(state: Boolean) {
        systemSharedPreferences.edit().putBoolean(IS_FIRST_LAUNCH, state).apply()
    }

    companion object {
        private const val IS_FIRST_LAUNCH = "is_first_launch"
    }
}