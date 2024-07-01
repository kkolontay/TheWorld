package com.kodeco.android.countryinfo.dao.database.repository
import com.kodeco.android.countryinfo.models.Country
import kotlinx.coroutines.flow.Flow

interface CountryDAORepository {
   fun getCountries(): Flow<List<Country>>

   suspend fun addCountries(country: List<Country>)

   fun getCountry( name: String): Flow<Country>

   suspend fun deleteAllCountries()
}