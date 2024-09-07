package com.example.currencyapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.currencyapp.databinding.RatesListItemBinding

// a view holder inherits from view holder
// the view holder needs a view as a parameter. we have to convert our xml file into a view using the view binding feature
// view binding would generate a separate class for each xml file
class RatesViewHolderItemRate(
    // import the class created for our rate_list_item xml
    binding: RatesListItemBinding
): RatesViewHolderBase<RatesListItemBinding>(
    // pass the biding into the base class
    binding
) {
    // function to map rate item to ui.
    fun bind(item: RatesItem) {
        with(binding) {
            // set textRate(defined in xml)
            textRate.text = item.rateValue
            // set textSymbolCode(defined in xml)
            textSymbolCode.text = item.symbolCode
        }
    }
}