package com.example.currencyapp.use_case

import com.example.currencyapp.RatesItem
import com.example.currencyapp.model.RatesRepository

class GetRatesUseCase {

    private val ratesRepository = RatesRepository()

    fun getRates(): List<RatesItem> {
        return ratesRepository.getRates()
    }
}