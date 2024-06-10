package com.kkolontay.theworld.repository

import com.kkolontay.theworld.api.CountryInfoState
import com.kkolontay.theworld.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun fetchCountries(): Flow<CountryInfoState>
    suspend fun updateListCountries()
    fun getCountry(name: String): Country
}