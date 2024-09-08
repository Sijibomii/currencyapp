package com.example.use_case

import com.example.common.model.SymbolItem
import com.example.rates_repository.RatesRepository
import javax.inject.Inject

class GetAvailableSymbolsUseCase @Inject constructor(
    private val ratesRepository: RatesRepository
) {
    fun getAvailableSymbols(): List<SymbolItem> =
        ratesRepository.getAvailableSymbols()
}