package com.example.currencyapp

import com.example.currencyapp.model.RatesRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesRepositoryTest {

    @Test
    fun getRatesTest() {
        val repository: RatesRepository = RatesRepository()
        val rates1 = repository.getRates()
        val rates2 = repository.getRates()

        assertEquals(rates1.size, rates2.size)
    }

    @Test
    fun getRatesAdvancedTest() {
        val repository = RatesRepository()

        val rates1 = repository.getRates()
        val rates2 = repository.getRates()

        rates1.indices.forEach {
            assertEquals(rates1[it].symbolCode, rates2[it].symbolCode)
            assertEquals(rates1[it].rateValue, rates2[it].rateValue)
        }
    }
}