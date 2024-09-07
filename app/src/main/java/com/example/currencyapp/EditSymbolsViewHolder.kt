package com.example.currencyapp

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.databinding.EditSymbolsItemViewBinding

/**
the RecyclerView uses a recycling mechanism to reuse view holders as you scroll through the list.
This is crucial for performance, especially with large data sets, as creating and binding a new view
holder for each item would be resource-intensive.

When you initialize a RecyclerView, it doesn’t create a view holder for each item immediately.
Instead, it only creates enough view holders to display the visible items.
As you scroll, the view holders that go off-screen are recycled and used again for new items coming into view.

the method onBindViewHolder() is called to bind data to a ViewHolder.
This method is responsible for associating the data at a particular position in the list with a specific view holder.

When a view holder is recycled, it’s not recreated, but its contents are updated by calling the bind method.
This means that when a view holder is reused, the new data (such as the SymbolItem and the selected state) is
passed to the bind() function, which updates the visual components (like the symbol code and checkbox).
 */

// EditSymbolsViewHolder  is responsible for managing and updating the visual representation of individual
// list items within the RecyclerView it manages in this case EditSymbolsItemView binded by EditSymbolsItemViewBinding.

// The view holder receives a binding object for the layout file associated with an individual list item (in this case, the layout is defined by EditSymbolsItemViewBinding).
// The binding object allows easy access to views in the layout without needing to call findViewById().

//  The root view of the layout is passed to the superclass (RecyclerView.ViewHolder) for use in displaying the item.
class EditSymbolsViewHolder(
    private val binding: EditSymbolsItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    // The bind function is where the data for each list item is passed to the view holder and displayed in the UI.
    fun bind(item: SymbolItem, selected: Boolean, onClicked: (String, Boolean) -> Unit) {
        with(binding) {
            textSymbolCode.text = item.code
            checkBoxSelectedSymbol.setOnCheckedChangeListener(null)
            checkBoxSelectedSymbol.isChecked = selected
            checkBoxSelectedSymbol.setOnCheckedChangeListener { _, isChecked ->
                onClicked(item.code, isChecked)
            }
        }
    }
}