package com.example.user_settings

import com.example.common.model.SymbolItem

interface UserSettings {
    fun getSelectedSymbols(): List<SymbolItem>
    fun setSelectedSymbols(symbols: List<SymbolItem>)
}