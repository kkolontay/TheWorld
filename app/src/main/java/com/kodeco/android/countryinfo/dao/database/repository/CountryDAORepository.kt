package com.kodeco.android.countryinfo.dao.database.repository
import com.kodeco.android.countryinfo.models.Country
import kotlinx.coroutines.flow.Flow

interface CountryDAORepository {
   suspend fun getCountries(): List<Country>

   suspend fun addCountries(country: List<Country>)

   suspend fun getCountry( name: String): Country

   suspend fun deleteAllCountries()
}