package com.example.currencyapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.example.currencyapp.use_case.GetSelectedRatesUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val getRatesUseCase: GetSelectedRatesUseCase
): ViewModel() {


    // kotlin state flow helps to manage and observe changes in data over time.
    // it allows you to create a stream of values that can be observed by other componets in your code
    // this is a mutable state flow variable that represents the current state of the list of rates item
    // it is initialized with an emptyList() and since its mutable we can update it's value when needed.
    private val _rates: MutableStateFlow<List<RatesItem>> = MutableStateFlow(emptyList())
    // this is a read only state variable. it is derived from the mutable one using the .asStateFlow() function
    // any change made to _rates would automatically reflect in rates
    val rates: StateFlow<List<RatesItem>> = _rates.asStateFlow()

    fun getRates() {
        _rates.tryEmit(getRatesUseCase.getRates())
    }
}