package com.kkolontay.theworld.api

import com.kkolontay.theworld.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
        @GET("all")
        suspend fun getAllCountry(): Response<List<Country>>
}


sealed class CountryInfoState {
        class Success(val list: List<Country>): CountryInfoState()
        class Loading(): CountryInfoState()
        class Error(val description: String?): CountryInfoState()

}