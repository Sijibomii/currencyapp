package com.example.currencyapp

import com.example.rates_repository.RatesRepository
import com.example.rates_repository_impl.RatesRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesRepositoryTest {
    @Test
    fun getRatesTest() {
        val repository: RatesRepository = RatesRepositoryImpl()

        val symbols = repository.getAvailableSymbols()
        val rates2 = repository.getRates(symbols)

        assertEquals(rates2.size, symbols.size)
        rates2.indices.forEach {
            assertEquals(rates2[it].symbolCode, symbols[it].code)
        }
    }

    @Test
    fun testGetRatesBySelectedSymbols() {
        val repository: RatesRepository = RatesRepositoryImpl()

        val symbols = repository.getAvailableSymbols().take(5)
        val rates1 = repository.getRates(symbols)

        assertEquals(rates1.size, symbols.size)
        rates1.indices.forEach {
            assertEquals(rates1[it].symbolCode, symbols[it].code)
        }
    }
}