package com.lccoding.currencyapp.presentation.ui.curreny_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lccoding.currencyapp.databinding.FragmentCurrencyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyListBinding
    private val viewModel by activityViewModels<CurrencyViewModel>()
    private lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currencyList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.currencyList.addItemDecoration(
            DividerItemDecoration(
                context, RecyclerView
                    .VERTICAL
            )
        )
        binding.stateText.text = "Please tab \"Fetch List\" button"

        adapter = CurrencyAdapter(mutableListOf())
        binding.currencyList.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when {
                it.isLoading -> {
                    // use a timer
                    showLoading()
                }
                it.currencies.isNotEmpty() -> {
                    hideLoading()
                    adapter.swapData(it.currencies)
                }
                it.error.isNotEmpty() -> {
                    showError(it.error)
                }
            }
        })

        adapter.setOnItemTapListener { currency ->
            viewModel.selectItem(currency)
        }
    }

    private fun showLoading() {
        binding.progress.visibility = VISIBLE
        binding.stateText.visibility = VISIBLE
        binding.stateText.text = "Loading"
    }

    private fun hideLoading() {
        binding.progress.visibility = GONE
        binding.stateText.visibility = GONE
    }

    private fun showError(message: String) {
        binding.progress.visibility = GONE
        binding.stateText.visibility = VISIBLE
        binding.stateText.text = message
    }
}