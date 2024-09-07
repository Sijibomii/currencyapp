package com.example.currencyapp.use_case

import com.example.currencyapp.RatesItem
import com.example.currencyapp.UserSettings
import com.example.currencyapp.model.RatesRepository
import javax.inject.Inject

class GetSelectedRatesUseCase @Inject constructor(
    private val repository: RatesRepository,
    private val userSettings: UserSettings
) {

    fun getRates(): List<RatesItem> {
        return repository.getRates(
            userSettings.getSelectedSymbols()
        )
    }
}