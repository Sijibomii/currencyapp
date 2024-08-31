package com.example.currencyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyapp.databinding.FragmentRatesBinding
import kotlin.random.Random;
/**
 * A simple [Fragment] subclass.
 * Use the [RatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RatesFragment : Fragment() {
    // to access views defined in the xml layout
    private lateinit var binding: FragmentRatesBinding

    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRatesBinding.inflate(inflater, null, false )
        // Inflate the layout for this fragment
        return binding.root
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
            adapter = RatesListAdapter().apply {
                updateList(generateMockRates())
            }
        }
    }

    // dummy data generator
    private fun generateMockRates(): List<RatesItem> {
        return (0 until 100)
            .map {
                RatesItem(
                    symbolCode = mockSymbolCode(),
                    rateValue = String.format("%.4f", Random.nextInt(10000, 99999) / 10000f)
                )
            }
    }
    // generate random currency codes
    private fun mockSymbolCode(): String {
        return (0 until 3).map { Random.nextInt(65, 90).toChar() }.joinToString(separator = "")
    }
}