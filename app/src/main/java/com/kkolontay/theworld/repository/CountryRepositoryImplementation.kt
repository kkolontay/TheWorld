package com.kkolontay.theworld.repository
import com.kkolontay.theworld.api.ApiBuilder
import com.kkolontay.theworld.api.CountryInfoState
import com.kkolontay.theworld.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class CountryRepositoryImplementation: CountryRepository {

    private var countries: List<Country> = listOf()
    private val apiService = ApiBuilder.apiService
    private var collector: FlowCollector<CountryInfoState>? = null

    override suspend fun fetchCountries(): Flow<CountryInfoState> {
        return flow {
            collector = this
            emit(CountryInfoState.Loading())
            val response = apiService.getAllCountry()
            if (response.isSuccessful) {
                countries = response.body() ?: listOf()
                emit(CountryInfoState.Success(countries))
            } else {
                emit(CountryInfoState.Error(response.errorBody().toString()))
            }
        }
    }

    override suspend fun updateListCountries() {
        if (collector != null) {
            collector!!.emit(CountryInfoState.Loading())
            val response = apiService.getAllCountry()
            if (response.isSuccessful) {
                countries = response.body() ?: listOf()
                collector!!.emit(CountryInfoState.Success(countries))
            } else {
                collector!!.emit(CountryInfoState.Error(response.errorBody().toString()))
            }
        }
    }

    override fun getCountry(name: String): Country {
        return countries.first {
            it.name.common == name
        }
    }
}