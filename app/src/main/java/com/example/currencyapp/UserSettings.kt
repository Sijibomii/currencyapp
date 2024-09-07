package com.example.currencyapp

interface UserSettings {
    fun getSelectedSymbols(): List<SymbolItem>
    fun setSelectedSymbols(symbols: List<SymbolItem>)
}