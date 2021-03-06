package com.lccoding.currencyapp.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationBarView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lccoding.currencyapp.R
import com.lccoding.currencyapp.common.SharedPreferenceUtil
import com.lccoding.currencyapp.data.local.CurrencyInfo
import com.lccoding.currencyapp.databinding.ActivityMainBinding
import com.lccoding.currencyapp.presentation.ui.curreny_list.CurrencyListFragmentDirections
import com.lccoding.currencyapp.presentation.ui.curreny_list.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val currencyViewModel by viewModels<CurrencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        setupBottomNavBar()
        setupSelectedItemObserver()
        initFirstLaunchData()
    }

    private fun setupSelectedItemObserver() {
        currencyViewModel.selectedItem.observe(this, Observer { currency ->
            Toast.makeText(this, "Selected ${currency.name}", Toast.LENGTH_SHORT).show()
            currency?.let {
                val direction =
                    CurrencyListFragmentDirections.actionCurrencyListFragmentToCurrencyDetailFragment(
                        currency
                    )
                navController.navigate(direction)
            }
        })
    }

    private fun setupBottomNavBar() {
        binding.mainBottomNavView.menu.setGroupCheckable(0, false, true)
        binding.mainBottomNavView.setOnItemSelectedListener(this)
    }

    private fun initFirstLaunchData() {
        if(SharedPreferenceUtil(application).isFirstLaunch()) {
            val jsonString = resources.openRawResource(R.raw.currencies).bufferedReader().use {
                it.readText()
            }
            val typeToken = object : TypeToken<List<CurrencyInfo>>() {}.type
            val currencies = Gson().fromJson<List<CurrencyInfo>>(jsonString, typeToken)
            currencyViewModel.addCurrencies(currencies, false)
            SharedPreferenceUtil(application).setFirstLaunch(false)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_bottom_fetch_list -> {
                currencyViewModel.getCurrencies()
            }
            R.id.navigation_bottom_sort_list -> {
                currencyViewModel.getSortedCurrencies()
            }
        }
        return true
    }
}