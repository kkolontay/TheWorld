package com.kkolontay.theworld.repository
import com.kkolontay.theworld.api.ApiBuilder
import com.kkolontay.theworld.api.CountryInfoState
import com.kkolontay.theworld.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CountryRepositoryImplementation: CountryRepository {

    private var countries: List<Country> = listOf()
    private val apiService = ApiBuilder.apiService
    private var collector = flow {
        emit(CountryInfoState.Loading())
        try {
            val response = apiService.getAllCountry()
            if (response.isSuccessful) {
                countries = response.body() ?: listOf()
                emit(CountryInfoState.Success(countries))
            } else {
                emit(CountryInfoState.Error(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            emit(CountryInfoState.Error(e.localizedMessage))
        }
    }

    override suspend fun fetchCountries(): Flow<CountryInfoState> {
        return collector
    }


    override fun getCountry(name: String): Country {
        return countries.first {
            it.name.common == name
        }
    }
}