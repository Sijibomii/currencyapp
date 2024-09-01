package com.example.currencyapp.model

import com.example.currencyapp.RatesItem
import kotlin.random.Random

class RatesRepository {
    private val data = generateMockRates()

    fun getRates(): List<RatesItem> {
        return data
    }

    // dummy data generator
    private fun generateMockRates(): List<RatesItem> {
        return (0 until 100)
            .map {
                RatesItem(
                    symbolCode = mockSymbolCode(),
                    rateValue = String.format("%.4f", Random.nextInt(10000, 99999) / 10000f)
                )
            }
    }
    // generate random currency codes
    private fun mockSymbolCode(): String {
        return (0 until 3).map { Random.nextInt(65, 90).toChar() }.joinToString(separator = "")
    }
}