package com.example.currencyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.currencyapp.databinding.RatesListItemBinding

// inherits the base adapter from recycler view
// we need to pass in our view holder class
class RatesListAdapter: Adapter<RatesViewHolder>() {
    // holds the rate list
    private val ratesList = mutableListOf<RatesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(
            RatesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = ratesList.size

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        ratesList.getOrNull(position)?.let { holder.bind(it) }
    }
    // used to update the rate list
    fun updateList(data: List<RatesItem>) {
        ratesList.clear()
        ratesList.addAll(data)
        // notify data has changed
        notifyDataSetChanged()
    }
}