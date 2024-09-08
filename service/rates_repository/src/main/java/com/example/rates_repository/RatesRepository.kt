package com.example.rates_repository

import com.example.common.model.RatesItem
import com.example.common.model.SymbolItem

interface RatesRepository {
    fun getRates(symbols: List<SymbolItem>): List<RatesItem>
    fun getAvailableSymbols(): List<SymbolItem>
}