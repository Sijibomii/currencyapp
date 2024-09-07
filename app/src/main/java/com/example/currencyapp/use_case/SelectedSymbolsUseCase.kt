package com.example.currencyapp.use_case

import com.example.currencyapp.SymbolItem
import com.example.currencyapp.UserSettings
import javax.inject.Inject

class SelectedSymbolsUseCase  @Inject constructor(
    private val userSettings: UserSettings
) {
    fun getSelectedSymbols(): List<SymbolItem> =
        userSettings.getSelectedSymbols()

    fun updateSelectedSymbols(symbols: List<SymbolItem>) {
        userSettings.setSelectedSymbols(symbols)
    }
}