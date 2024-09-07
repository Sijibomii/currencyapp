package com.example.currencyapp.screen.rates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyapp.R
import com.example.currencyapp.databinding.FragmentRatesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [RatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/**
 * The RatesFragment class is responsible for displaying a list of currency rates to the user.
 * It leverages a RecyclerView to show the list and provides functionality to add new currencies through an adapter.
 * The fragment interacts with a RatesViewModel to fetch and observe the data, ensuring that the UI
 * remains up-to-date with the latest rates.
 */
// NOTE: A fragment is basically a page or screen
// @AndroidEntryPoint annotation is part of Dagger-Hilt, a dependency injection library for Android.
// It signifies that RatesFragment is a Hilt-managed component, allowing Hilt to inject dependencies into it.
// the purpose is to simplify dependency injection by automatically handling the creation and provisioning of dependencies.
@AndroidEntryPoint
// RatesFragment extends Android's Fragment class, making it a modular and reusable UI component within an activity.
// it represents a portion of the user interface in an activity, managing its own lifecycle.
class RatesFragment : Fragment() {
    // to access views defined in the xml layout
    // Utilizes View Binding to interact with the views defined in the fragment's XML layout (fragment_rates.xml)
    // the lateinit: Indicates that the variable will be initialized later, specifically in onCreateView
    private lateinit var binding: FragmentRatesBinding
    // adapter init code
    // Initializes an instance of RatesListAdapter, passing a lambda function that defines the action
    // to perform when the "Add Currency" button is clicked.
    // onAddNewCurrencyClicked() is invoked when the user interacts with the add button in the RecyclerView.
    private val ratesAdapter = RatesListAdapter{
        onAddNewCurrencyClicked()
    }
    // Uses Kotlin's property delegation to lazily initialize the RatesViewModel
    // by viewModels(): A delegate that provides a ViewModel scoped to this Fragment.
    // Hilt injects the RatesViewModel into this fragment.
    // The by viewModels() delegate is a convenient way to obtain the ViewModel in the fragment.
    private val viewModel: RatesViewModel by viewModels()

    // An initializer block that's executed when an instance of RatesFragment is created.
    init {
        // Launches a coroutine tied to the Fragment's lifecycle.
        // Ensures that the coroutine is canceled when the Fragment's lifecycle is destroyed, preventing memory leaks.
        lifecycleScope.launch {
            // This ensures that the collect block runs only when the fragment is in the RESUMED state,
            // pausing the flow collection when the fragment is not visible (stopping unnecessary work).
            // it ensures that the data collection happens only when the Fragment is active and visible to the user.
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                // The fragment is collecting the rates flow from the ViewModel.
                // Whenever the list of rates changes, the adapter’s list is updated by calling
                // ratesAdapter.updateList(rates).
                viewModel.rates.collect { rates ->
                    ratesAdapter.updateList(rates)
                }
            }
        }
    }


    override fun onCreateView(
        // inflater: The LayoutInflater object that can be used to inflate any views.
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflates the layout using View Binding.
        binding = FragmentRatesBinding.inflate(inflater, null, false )
        // Inflate the layout for this fragment
        // Returns the root view of the inflated layout, which becomes the Fragment's view.
        return binding.root
    }

    // Called when the Fragment becomes visible and interactive to the user.
    override fun onResume() {
        super.onResume()
        // Triggers the ViewModel to fetch the latest rates, ensuring that the displayed data is up-to-date when the Fragment resumes.
        viewModel.getRates()
    }

    // The onViewCreated method is overridden to customize what happens after the fragment's view has been created.
    // This method is called immediately after onCreateView. It’s where you typically set up your views and UI components.
    // The view parameter is the root view of the fragment, and savedInstanceState contains any saved state information.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This with block is used to operate on the recyclerRates object, which is a RecyclerView referenced from the fragment's layout.
        // binding refers to the view binding object, which provides access to the views in the fragment's layout.
        with(binding.recyclerRates) {
            // This sets the layout manager for the RecyclerView.
            // In this case, LinearLayoutManager is used, which arranges the items in a linear list (either vertically or horizontally).
            layoutManager = LinearLayoutManager(context)
            // This sets the adapter for the RecyclerView. RatesListAdapter is a custom adapter class that manages how data is displayed in the RecyclerView.
            adapter = ratesAdapter
        }
    }

    private fun onAddNewCurrencyClicked() {
        // by clicking the button this function is run and we want to navigate the user to another fragment
        // so they can select the currency they want.
        findNavController().navigate(R.id.editSymbolsFragment)
    }
}