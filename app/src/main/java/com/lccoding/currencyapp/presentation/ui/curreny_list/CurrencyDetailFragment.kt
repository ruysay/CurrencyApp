package com.lccoding.currencyapp.presentation.ui.curreny_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.lccoding.currencyapp.databinding.FragmentCurrencyDetailBinding

class CurrencyDetailFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyDetailBinding
    private val args: CurrencyDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            it.findNavController().popBackStack()
        }
        // Get data from previous fragment
        val currency = args.currentyItem
        binding.detailStatement.text = currency.name
        context ?: return binding.root
        return binding.root
    }
}