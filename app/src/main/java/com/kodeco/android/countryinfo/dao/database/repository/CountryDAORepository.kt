package com.kodeco.android.countryinfo.dao.database.repository
import com.kodeco.android.countryinfo.models.Country

interface CountryDAORepository {
    fun getCountries(): List<Country>

    fun addCountry(country: Country)

    fun getCountry( name: String): Country
}