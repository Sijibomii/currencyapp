package com.example.currencyapp.screen.rates

import com.example.currencyapp.databinding.RatesListAddItemBinding
// A ViewHolder is a class used to represent the views for each item in a RecyclerView.
// It holds references to the views in the layout and can be reused when scrolling through the list, improving performance.
// Without ViewHolder, a new view would be created every time an item scrolls into view.
// ViewHolder reduces the number of unnecessary calls to findViewById() by holding references to the views.
// ViewHolders allow the RecyclerView to recycle the item views that are no longer visible on the screen.
// Instead of inflating new views, the RecyclerView reuses the existing ones, improving scrolling performance.
class RatesViewHolderAddItem(
    // This is a binding class generated for the layout XML file
    // It allows you to access the views directly without needing to use findViewById
    binding: RatesListAddItemBinding,
): RatesViewHolderBase<RatesListAddItemBinding>(binding) {
    //The bind method is commonly used in RecyclerView ViewHolders to associate data with the views of each item in a list.
    // This method is responsible for taking the relevant data and applying it to the UI components
    // (such as TextViews, ImageViews, Buttons, etc.) of the ViewHolder.

    // It binds data from the data model to the views of the ViewHolder.
    // It is called every time a new item is displayed in the list to ensure that the correct data is shown for each item.
    // basically this helps set the state for each individual component
    fun bind(onClick: ()-> Unit) {
        // This sets up a click listener for a button (btnAddRatesItem) in the bound layout.
        // When the button is clicked, it executes the onClick function that was passed to the bind method.
        binding.btnAddRatesItem.setOnClickListener{
            onClick()
        }
    }
}