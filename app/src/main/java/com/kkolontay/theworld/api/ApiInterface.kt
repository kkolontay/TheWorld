package com.kkolontay.theworld.api

import com.kkolontay.theworld.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
        @GET("all")
        suspend fun getAllCountry(): Response<List<Country>>
}

sealed class WorldResponse {
        class Success(val list: List<Country>): WorldResponse()
        class Loading(): WorldResponse()
        class Error(val description: String?): WorldResponse()

}