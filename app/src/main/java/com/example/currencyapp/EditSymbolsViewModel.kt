package com.example.currencyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapp.use_case.GetAvailableSymbolsUseCase
import com.example.currencyapp.use_case.SelectedSymbolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// A ViewModel in Android is part of the MVVM (Model-View-ViewModel) architecture.
// It serves as the link between the UI (the view) and the data model (or use cases).
// The ViewModel holds data that the UI can observe and react to.
// ViewModels are lifecycle-aware, meaning they survive configuration
// changes (such as screen rotations) and continue to hold and manage data efficiently.

// @HiltViewModel: This annotation is part of Dagger-Hilt, a dependency injection library.
// It tells Hilt that this is a ViewModel, enabling automatic injection of dependencies into the ViewModel.
@HiltViewModel
// @Inject constructor(...): This is Hilt's way of injecting dependencies.
// The getAvailableSymbolsUseCase and selectedSymbolsUseCase are injected into the ViewModel automatically by Hilt.
// The EditSymbolsViewModel extends Android’s ViewModel class, making it lifecycle-aware and capable of managing UI-related data efficiently.
class EditSymbolsViewModel @Inject constructor(
    private val getAvailableSymbolsUseCase: GetAvailableSymbolsUseCase,
    private val selectedSymbolsUseCase: SelectedSymbolsUseCase
) : ViewModel() {
    // this is a private MutableStateFlow that holds the internal state of the selected and available symbols.
    // MutableStateFlow is a state holder that emits values to its collectors when the state changes
    // MutableStateFlow Can emit new values and be updated inside the ViewModel.
    private val _selectedSymbols: MutableStateFlow<SelectedSymbols> =
        MutableStateFlow(SelectedSymbols(emptyList(), emptyList()))
    // This is a publicly exposed StateFlow that the UI can observe.
    // Since StateFlow is immutable, the UI can only read from it, ensuring that the state is managed only within the ViewModel.
    // StateFlow is a read-only version of MutableStateFlow, which the UI observes.
    val selectedSymbols: StateFlow<SelectedSymbols> = _selectedSymbols.asStateFlow()

    // This function is responsible for fetching the currently selected symbols
    // and available symbols from two different use cases and then updating the state.
    fun fetchSelectedSymbols() {
        // viewModelScope is a coroutine scope provided by the ViewModel
        // that is automatically canceled when the ViewModel is cleared (i.e., when the lifecycle ends).
        // .launch launches a coroutine in the IO dispatcher, which is optimized for I/O-bound tasks (like database or network operations).
        viewModelScope.launch(Dispatchers.IO) {
            //  this pdates the internal state (_selectedSymbols) with the new data fetched from the use cases.
            //  This triggers an update in any UI components observing the selectedSymbols StateFlow.
            _selectedSymbols.emit(
                // emits the selected symbols and stores
                SelectedSymbols(
                    selectedSymbolsUseCase.getSelectedSymbols(),
                    getAvailableSymbolsUseCase.getAvailableSymbols()
                )
            )
        }
    }
    // This function updates the selected symbols based on user input (e.g., selecting or unselecting a symbol).
    // It either adds a new symbol or removes an existing one from the selected list.
    fun updateSelectedSymbols(code: String, isChecked: Boolean) {
        val selectedSymbols = selectedSymbolsUseCase.getSelectedSymbols()
        if (isChecked) { // add symbol to the selected symbols
            selectedSymbolsUseCase.updateSelectedSymbols(selectedSymbols.plus(SymbolItem(code)))
        } else { // remove symbol
            selectedSymbolsUseCase.updateSelectedSymbols(selectedSymbols.filter { it.code != code })
        }
    }

    data class SelectedSymbols(
        val selectedSymbols: List<SymbolItem>,
        val availableSymbols: List<SymbolItem>,
    )
}