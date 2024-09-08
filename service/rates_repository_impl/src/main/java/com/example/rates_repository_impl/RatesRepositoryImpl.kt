package com.example.rates_repository_impl

import com.example.common.model.RatesItem
import com.example.common.model.SymbolItem
import com.example.rates_repository.RatesRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

// the @Inject tells hilt to provide an instance of this class whenever it's required
// @Singleton tells hilt to create only one version of this to be shared by every one
@Singleton
class RatesRepositoryImpl @Inject constructor() : RatesRepository {
    private val data = generateMockRates()

    override fun getRates(symbols: List<SymbolItem>): List<RatesItem> {
        return data.filter { symbols.any { symbol -> symbol.code == it.symbolCode } }
    }

    override fun getAvailableSymbols() :List<SymbolItem> =
        data.map { SymbolItem(it.symbolCode) }

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