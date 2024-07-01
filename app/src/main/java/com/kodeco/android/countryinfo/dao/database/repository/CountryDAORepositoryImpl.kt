package com.kodeco.android.countryinfo.dao.database.repository

import com.kodeco.android.countryinfo.dao.database.CountriesDAO
import com.kodeco.android.countryinfo.models.Country

class CountryDAORepositoryImpl(private val countriesDao: CountriesDAO): CountryDAORepository {
    override fun getCountries(): List<Country> {
        TODO("Not yet implemented")
    }

    override fun addCountry(country: Country) {
        TODO("Not yet implemented")
    }

    override fun getCountry(name: String): Country {
        TODO("Not yet implemented")
    }
}