package com.example.currencyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.currencyapp.databinding.FragmentChartsBinding
import dagger.hilt.android.AndroidEntryPoint

//When you use @AndroidEntryPoint, you are telling Hilt to automatically generate and inject the necessary components into the annotated Android class
@AndroidEntryPoint
class ChartsFragment : Fragment() {
    // Type: FragmentChartsBinding is an auto-generated class that corresponds to the XML layout file for the fragment (fragment_charts.xml)
    // It provides direct references to all the views in that layout, making it easier to work with them in Kotlin code.
    // The ? denotes that this variable can be null.
    // This is important because the binding should be initialized when the fragment's view is created and cleaned up (set to null) when the view is destroyed to avoid memory leaks.
    private var _binding: FragmentChartsBinding? = null
    // This declares a read-only (val) property binding of type FragmentChartsBinding.
    // Instead of storing a value directly, this property uses a custom getter.
    // This getter returns the value of _binding, but with a special condition.
    // referencing binding triggers the get() method
    private val binding: FragmentChartsBinding
        // This is a custom getter for the binding property. When you access binding, this getter is called.
        // The !! operator is a Kotlin feature that asserts that _binding is not null when accessed.
        // If _binding is null, the app will throw a NullPointerException (NPE) at runtime.
        get() = _binding!!

    private val viewModel: ChartsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChartsBinding.inflate(inflater, container, false)
        return binding.root
    }
    // set binding to null to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}