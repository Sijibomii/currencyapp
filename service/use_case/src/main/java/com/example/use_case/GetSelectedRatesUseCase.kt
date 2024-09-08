package com.example.use_case

import com.example.common.model.RatesItem
import com.example.rates_repository.RatesRepository
import javax.inject.Inject

class GetSelectedRatesUseCase @Inject constructor(
    private val repository: RatesRepository,
    private val userSettings: com.example.user_settings.UserSettings
) {

    fun getRates(): List<RatesItem> {
        return repository.getRates(
            userSettings.getSelectedSymbols()
        )
    }
}