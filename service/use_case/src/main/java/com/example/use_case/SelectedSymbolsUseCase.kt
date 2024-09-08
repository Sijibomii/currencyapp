package com.example.use_case

import com.example.common.model.SymbolItem
import com.example.user_settings.UserSettings
import javax.inject.Inject

class SelectedSymbolsUseCase  @Inject constructor(
    private val userSettings: com.example.user_settings.UserSettings
) {
    fun getSelectedSymbols(): List<SymbolItem> =
        userSettings.getSelectedSymbols()

    fun updateSelectedSymbols(symbols: List<SymbolItem>) {
        userSettings.setSelectedSymbols(symbols)
    }
}