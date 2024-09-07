package com.example.currencyapp.use_case

import com.example.currencyapp.SymbolItem
import com.example.currencyapp.model.RatesRepository
import javax.inject.Inject

class GetAvailableSymbolsUseCase @Inject constructor(
    private val ratesRepository: RatesRepository
) {
    fun getAvailableSymbols(): List<SymbolItem> =
        ratesRepository.getAvailableSymbols()
}