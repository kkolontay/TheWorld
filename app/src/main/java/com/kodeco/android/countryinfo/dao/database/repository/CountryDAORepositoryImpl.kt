package com.kodeco.android.countryinfo.dao.database.repository

import com.kodeco.android.countryinfo.dao.database.CountriesDAO
import com.kodeco.android.countryinfo.models.Country
import kotlinx.coroutines.flow.Flow

class CountryDAORepositoryImpl(private val countriesDao: CountriesDAO): CountryDAORepository {
    override fun getCountries(): Flow<List<Country>> = countriesDao.getCountries()

    override suspend fun addCountries(country: List<Country>) = countriesDao.addCountries(country)

    override fun getCountry(name: String): Flow<Country> =  countriesDao.getCountry(name)

    override suspend fun deleteAllCountries() = countriesDao.deleteAllCountries()
}