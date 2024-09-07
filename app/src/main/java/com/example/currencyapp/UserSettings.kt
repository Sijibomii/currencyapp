package com.example.currencyapp

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSettings @Inject constructor() {
    private val selectedSymbols: MutableList<SymbolItem> = mutableListOf()

    fun getSelectedSymbols(): List<SymbolItem> = selectedSymbols.toList()

    fun setSelectedSymbols(symbols: List<SymbolItem>) {
        with(selectedSymbols) {
            clear()
            addAll(symbols)
        }
    }
}