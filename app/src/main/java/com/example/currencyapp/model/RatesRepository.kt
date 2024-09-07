package com.example.currencyapp.model

import com.example.currencyapp.RatesItem
import com.example.currencyapp.SymbolItem

interface RatesRepository {
    fun getRates(symbols: List<SymbolItem>): List<RatesItem>
    fun getAvailableSymbols(): List<SymbolItem>
}