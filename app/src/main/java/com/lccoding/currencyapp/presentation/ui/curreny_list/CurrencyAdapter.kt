package com.lccoding.currencyapp.presentation.ui.curreny_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lccoding.currencyapp.databinding.ItemCurrencyBinding
import com.lccoding.currencyapp.domain.model.Currency

class CurrencyAdapter(private val currencies: MutableList<Currency>) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var listener: ((Currency) -> Unit)? = null

    fun swapData(currencies: List<Currency>) {
        this.currencies.clear()
        this.currencies.addAll(currencies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(currencies[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun setOnItemTapListener(listener: ((Currency) -> Unit)) {
        this.listener = listener
    }

    class ViewHolder(
        private val binding: ItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Currency, listener: ((Currency) -> Unit)?) {
            binding.initial.text = "${item.name.first()}"
            binding.name.text = "${item.name}"
            binding.symbol.text = "${item.symbol}"

            binding.itemContainer.setOnClickListener {
                listener?.invoke(item)
            }
        }
    }
}