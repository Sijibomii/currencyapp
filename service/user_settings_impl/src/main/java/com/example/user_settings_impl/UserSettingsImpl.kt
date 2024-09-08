package com.example.user_settings_impl

import com.example.common.model.SymbolItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSettingsImpl @Inject constructor() : com.example.user_settings.UserSettings {
    private val selectedSymbols: MutableList<SymbolItem> = mutableListOf()

    override fun getSelectedSymbols(): List<SymbolItem> = selectedSymbols.toList()

    override fun setSelectedSymbols(symbols: List<SymbolItem>) {
        with(selectedSymbols) {
            clear()
            addAll(symbols)
        }
    }
}