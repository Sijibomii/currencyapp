package com.example.currencyapp.screen.rates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewbinding.ViewBinding
import com.example.common.model.RatesItem
import com.example.currencyapp.databinding.RatesListAddItemBinding
import com.example.currencyapp.databinding.RatesListItemBinding

// The adapter holds the list of currency rates (ratesList), along with a special item for adding a new currency
// It dynamically inflates and binds views for both the rate items and the "Add Currency" item based on the item type (getItemViewType).
// The onBindViewHolder method either binds the rate data or attaches the onAddCurrency click listener to the appropriate item.

// inherits the base adapter from recycler view
// we need to pass in our view holder class
// RatesListAdapter is a subclass of RecyclerView.Adapter, which manages and binds data to the views in a RecyclerView.
// It takes a lambda function onAddCurrency as a parameter,
// which is triggered when the user interacts with an "Add Currency" button (used later in the RatesViewHolderAddItem).
class RatesListAdapter(
    private val onAddCurrency: () -> Unit
): Adapter<RatesViewHolderBase<*>>(){
    // holds the rate list
    // This list is used to populate the RecyclerView
    private val ratesList = mutableListOf<RatesItem>()

    // This method is responsible for creating the appropriate ViewHolder for each list item,
    // depending on its type (either RatesViewHolderItemRate for rate items or RatesViewHolderAddItem
    // for the "Add Currency" item).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolderBase<*> {
        return  when (viewType) {
            // Created when viewType is ITEM_TYPE_RATES, and it inflates the layout for displaying a currency rate item
            ITEM_TYPE_RATES -> RatesViewHolderItemRate(
                RatesListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            // Created when the viewType is anything other than ITEM_TYPE_RATES (in this case, ITEM_TYPE_ADD).
            // It inflates the layout for the "Add Currency" item.
            else -> RatesViewHolderAddItem(
                RatesListAddItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        // If the position is within the bounds of the ratesList, it returns ITEM_TYPE_RATES.
        // Otherwise, it returns ITEM_TYPE_ADD, which is used to display the "Add Currency" button after all the rate items.
        return if (position in ratesList.indices) ITEM_TYPE_RATES else ITEM_TYPE_ADD
    }

    // This method returns the total number of items in the list.
    // It adds 1 to the size of ratesList to account for the "Add Currency" button,
    // which is always present at the end of the list.
    override fun getItemCount(): Int = ratesList.size + 1

    // This method binds the data or interaction to the views held by the ViewHolder
    override fun onBindViewHolder(holder: RatesViewHolderBase<*>, position: Int) {
        when (holder) {
            // If the holder is of type RatesViewHolderItemRate,
            // it binds the currency rate data to the item at the current position
            is RatesViewHolderItemRate -> ratesList.getOrNull(position)?.let { holder.bind(it) }
            // If the holder is of type RatesViewHolderAddItem, it binds the onAddCurrency function to the "Add Currency" button.
            is RatesViewHolderAddItem -> holder.bind(onAddCurrency)
        }
    }

    //  This method updates the ratesList with new data, clears the old list, and adds all items from the new list.
    //  It then calls notifyDataSetChanged() to refresh the UI and display the updated data in the RecyclerView.
    fun updateList(data: List<RatesItem>) {
        ratesList.clear()
        ratesList.addAll(data)
        // notify data has changed
        notifyDataSetChanged()
    }

    companion object {
        const val ITEM_TYPE_RATES = 10
        const val ITEM_TYPE_ADD = 20
    }
}

abstract class RatesViewHolderBase<VB: ViewBinding>(protected val binding: VB): RecyclerView.ViewHolder(binding.root)